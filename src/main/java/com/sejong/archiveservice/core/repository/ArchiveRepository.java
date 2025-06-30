package com.sejong.archiveservice.core.repository;

import com.sejong.archiveservice.core.model.Archive;

public interface ArchiveRepository {
    /**
     *
     * @param archive 저장할 Archive 객체
     * @return 저장된 Archive 객체
     */
    Archive save(Archive archive);

    /**
     *
     * @param archiveId archive id
     * @return 해당 archiveId를 가진 archive 존재 여부
     */
    boolean existsArchive(Long archiveId);
}
