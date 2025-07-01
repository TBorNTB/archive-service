package com.sejong.archiveservice.infrastructure.archive.repository;

import com.sejong.archiveservice.core.model.Archive;
import com.sejong.archiveservice.core.repository.ArchiveRepository;
import com.sejong.archiveservice.infrastructure.archive.entity.ArchiveEntity;
import com.sejong.archiveservice.infrastructure.archive.mapper.ArchiveMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArchiveRepositoryImpl implements ArchiveRepository {
    private final ArchiveJpaRepository archiveJpaRepository;

    @Override
    public Archive save(Archive archive) {
        ArchiveEntity entity = ArchiveMapper.toEntity(archive);
        return ArchiveMapper.toDomain(archiveJpaRepository.save(entity));
    }

    @Override
    public boolean existsArchive(Long archiveId) {
        // TODO: 구현
        return false;
    }
}
