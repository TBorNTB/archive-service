package com.sejong.archiveservice.core.news;

import com.sejong.archiveservice.core.common.pagination.CursorPageRequest;
import com.sejong.archiveservice.core.common.pagination.CursorPageResponse;
import com.sejong.archiveservice.core.common.pagination.CustomPageRequest;
import com.sejong.archiveservice.core.common.pagination.OffsetPageResponse;
import java.util.List;


public interface NewsRepository {
    /**
     *
     * @param archive 저장할 Archive 객체
     * @return 저장된 Archive 객체
     */
    News save(News archive);

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
    News findBy(Long archiveId);

    News update(News archive);

    void delete(News archive);

    OffsetPageResponse<List<News>> findAllWithOffset(CustomPageRequest customPageRequest);

    CursorPageResponse<List<News>> findAllWithCursor(CursorPageRequest cursorPageRequest);
}
