package com.sejong.archiveservice.application.archive.service;

import com.sejong.archiveservice.application.FileUploader;
import com.sejong.archiveservice.application.archive.dto.ArchiveReqDto;
import com.sejong.archiveservice.core.model.Archive;
import com.sejong.archiveservice.core.repository.ArchiveRepository;
import com.sejong.archiveservice.core.util.ArchiveIDGenerator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArchiveService {
    private final ArchiveRepository archiveRepository;
    private final FileUploader fileUploader;

    @Transactional
    public Archive create(ArchiveReqDto archiveReqDto) {
        Archive archive = Archive.builder()
                .id(ArchiveIDGenerator.generate())
                .content(archiveReqDto.content())
                .writerId(archiveReqDto.writerId())
                .participantIds(archiveReqDto.participants())
                .tags(archiveReqDto.tags())
                .likes(0)
                .view(0)
                .createdAt(java.time.LocalDate.now())
                .build();

        return archiveRepository.save(archive);
    }

    public void validateArchiveExists(Long archiveId) {
        // TODO: 실제 조회 로직 구현
    }
}
