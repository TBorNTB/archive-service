package com.sejong.archiveservice.application.archive.service;

import com.sejong.archiveservice.application.archive.assembler.ArchiveAssembler;
import com.sejong.archiveservice.application.archive.dto.ArchiveReqDto;
import com.sejong.archiveservice.application.file.FileUploader;
import com.sejong.archiveservice.core.model.Archive;
import com.sejong.archiveservice.core.repository.ArchiveRepository;
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
        Archive archive = ArchiveAssembler.toArchive(archiveReqDto);
        return archiveRepository.save(archive);
    }

    public void validateArchiveExists(Long archiveId) {
        // TODO: 실제 조회 로직 구현
    }
}
