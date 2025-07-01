package com.sejong.archiveservice.infrastructure.archive.repository;

import com.sejong.archiveservice.infrastructure.archive.entity.ArchiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchiveJpaRepository extends JpaRepository<ArchiveEntity, Long> {
    boolean existsById(Long archiveId);
}
