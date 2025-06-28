package com.sejong.archiveservice.infrastructure.repository;

import com.sejong.archiveservice.core.model.Archive;
import com.sejong.archiveservice.infrastructure.entity.ArchiveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ArchiveJpaRepository extends JpaRepository<ArchiveEntity, Long> {

}
