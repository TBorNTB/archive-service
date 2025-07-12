package com.sejong.archiveservice.infrastructure.archive.repository;

import com.sejong.archiveservice.infrastructure.archive.entity.ArchiveEntity;
import feign.Param;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArchiveJpaRepository extends JpaRepository<ArchiveEntity, Long> {
    boolean existsById(Long archiveId);

    Page<ArchiveEntity> findAll(Pageable pageable);

    // ID 기준 DESC 커서 페이지네이션
    @Query("SELECT a FROM ArchiveEntity a WHERE a.id < :cursor ORDER BY a.id DESC")
    List<ArchiveEntity> findByCursorDesc(@Param("cursor") Long cursor, Pageable pageable);

    // ID 기준 ASC 커서 페이지네이션
    @Query("SELECT a FROM ArchiveEntity a WHERE a.id > :cursor ORDER BY a.id ASC")
    List<ArchiveEntity> findByCursorAsc(@Param("cursor") Long cursor, Pageable pageable);

    // 첫 페이지 조회 (커서 없음)
    @Query("SELECT a FROM ArchiveEntity a ORDER BY a.id DESC")
    List<ArchiveEntity> findFirstPageDesc(Pageable pageable);

    @Query("SELECT a FROM ArchiveEntity a ORDER BY a.id ASC")
    List<ArchiveEntity> findFirstPageAsc(Pageable pageable);
}
