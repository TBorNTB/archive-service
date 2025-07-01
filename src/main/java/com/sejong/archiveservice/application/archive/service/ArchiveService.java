package com.sejong.archiveservice.application.archive.service;

import com.sejong.archiveservice.application.archive.assembler.ArchiveAssembler;
import com.sejong.archiveservice.application.archive.dto.ArchiveReqDto;
import com.sejong.archiveservice.application.file.FileUploader;
import com.sejong.archiveservice.core.model.Archive;
import com.sejong.archiveservice.core.repository.ArchiveRepository;
import com.sejong.archiveservice.infrastructure.user.UserServiceClient;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArchiveService {
    private final ArchiveRepository archiveRepository;
    private final UserServiceClient userServiceClient;
    private final FileUploader fileUploader;

    @Transactional
    public Archive create(ArchiveReqDto archiveReqDto) {
        validateUserExistence(archiveReqDto.writerId(), archiveReqDto.participantIds());

        Archive archive = ArchiveAssembler.toArchive(archiveReqDto);
        return archiveRepository.save(archive);
    }

    private void validateUserExistence(Long writerId, List<Long> participantIds) {
        if (!userServiceClient.exists(writerId)) {
            throw new IllegalArgumentException("존재하지 않는 작성자입니다.");
        }

        if (!userServiceClient.existsUsers(participantIds)) {
            throw new IllegalArgumentException("존재하지 않는 참여자가 있습니다.");
        }
    }

    public void validateArchiveExists(Long archiveId) {
        // TODO: 실제 조회 로직 구현
    }
}
