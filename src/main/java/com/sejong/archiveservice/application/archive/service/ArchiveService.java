package com.sejong.archiveservice.application.archive.service;

import com.sejong.archiveservice.application.archive.assembler.ArchiveAssembler;
import com.sejong.archiveservice.application.archive.dto.ArchiveReqDto;
import com.sejong.archiveservice.application.pagination.CursorPageReqDto;
import com.sejong.archiveservice.application.pagination.OffsetPageReqDto;
import com.sejong.archiveservice.core.common.pagination.CursorPageRequest;
import com.sejong.archiveservice.core.common.pagination.CursorPageResponse;
import com.sejong.archiveservice.core.common.pagination.CustomPageRequest;
import com.sejong.archiveservice.core.common.pagination.OffsetPageResponse;
import com.sejong.archiveservice.core.model.Archive;
import com.sejong.archiveservice.core.model.UserId;
import com.sejong.archiveservice.core.model.UserIds;
import com.sejong.archiveservice.core.repository.ArchiveRepository;
import com.sejong.archiveservice.infrastructure.user.UserServiceClient;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ArchiveService {
    private final ArchiveRepository archiveRepository;
    private final UserServiceClient userServiceClient;

    @Transactional
    public Archive create(ArchiveReqDto archiveReqDto) {
        validateUserExistence(archiveReqDto.writerId(), archiveReqDto.participantIds());

        Archive archive = ArchiveAssembler.toArchive(archiveReqDto);
        return archiveRepository.save(archive);
    }

    private void validateUserExistence(String writerId, List<String> participantIds) {
        if (!userServiceClient.exists(writerId)) {
            throw new IllegalArgumentException("존재하지 않는 작성자입니다.");
        }

        if (!userServiceClient.existsUsers(participantIds)) {
            throw new IllegalArgumentException("존재하지 않는 참여자가 있습니다.");
        }
    }

    public void validateArchiveExists(Long archiveId) {
        if (!archiveRepository.existsArchive(archiveId)) {
            throw new IllegalArgumentException("존재하지 않는 아카이브입니다.");
        }
    }

    @Transactional
    public Archive updateArchive(Long archiveId, ArchiveReqDto archiveReqDto, String writerId) {
        Archive archive = archiveRepository.findBy(archiveId);
        if (!archive.getWriterId().equals(UserId.of(writerId))) {
            throw new IllegalArgumentException("아카이브 작성자만 내용을 수정할 수 있습니다.");
        }
        archive.update(ArchiveAssembler.toContent(archiveReqDto),
                UserIds.of(archiveReqDto.participantIds()),
                archiveReqDto.tags());
        return archiveRepository.update(archive);
    }

    @Transactional
    public void deleteArchive(Long archiveId, String writerId) {
        Archive archive = archiveRepository.findBy(archiveId);
        if (!archive.getWriterId().equals(UserId.of(writerId))) {
            throw new IllegalArgumentException("아카이브 작성자만 내용을 수정할 수 있습니다.");
        }
        archiveRepository.delete(archive);
    }

    public Archive findById(Long archiveId) {
        return archiveRepository.findBy(archiveId);
    }

    public OffsetPageResponse<List<Archive>> getOffsetArchives(OffsetPageReqDto offsetPageReqDto) {
        CustomPageRequest pageRequest = offsetPageReqDto.toPageRequest();
        return archiveRepository.findAllWithOffset(pageRequest);
    }

    public CursorPageResponse<List<Archive>> getCursorArchives(CursorPageReqDto cursorPageReqDto) {
        CursorPageRequest pageRequest = cursorPageReqDto.toPageRequest();
        return archiveRepository.findAllWithCursor(pageRequest);
    }
}
