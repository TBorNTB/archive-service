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
        return archiveJpaRepository.existsById(archiveId);
    }

    @Override
    public Archive findBy(Long archiveId) {
        ArchiveEntity archiveEntity = archiveJpaRepository.findById(archiveId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 아카이브 ID"));
        return ArchiveMapper.toDomain(archiveEntity);
    }

    @Override
    public Archive update(Archive archive) {
        ArchiveEntity entity = ArchiveMapper.toEntity(archive);
        return ArchiveMapper.toDomain(archiveJpaRepository.save(entity));
    }


    @Override
    public void delete(Archive archive) {
        archiveJpaRepository.deleteById(archive.getId());
    }
}
