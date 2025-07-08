package com.sejong.archiveservice.core.repository;

import com.sejong.archiveservice.core.common.pagination.CursorPageRequest;
import com.sejong.archiveservice.core.common.pagination.CursorPageResponse;
import com.sejong.archiveservice.core.common.pagination.CustomPageRequest;
import com.sejong.archiveservice.core.common.pagination.OffsetPageResponse;
import com.sejong.archiveservice.core.model.Archive;
import java.util.List;


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

    /**
     *
     * @param archiveId archive id
     * @return 해당 Archive 객체
     */
    Archive findBy(Long archiveId);

    Archive update(Archive archive);

    void delete(Archive archive);

    OffsetPageResponse<List<Archive>> findAllWithOffset(CustomPageRequest customPageRequest);

    CursorPageResponse<List<Archive>> findAllWithCursor(CursorPageRequest cursorPageRequest);
}
