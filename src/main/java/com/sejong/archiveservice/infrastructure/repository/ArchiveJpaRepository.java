package com.sejong.archiveservice.infrastructure.repository;

import com.sejong.archiveservice.core.model.Archive;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchiveJpaRepository extends JpaRepository<Archive, Long> {

}
