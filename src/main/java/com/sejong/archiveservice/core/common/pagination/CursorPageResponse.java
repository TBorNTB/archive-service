package com.sejong.archiveservice.core.common.pagination;

import lombok.Getter;

@Getter
public class CursorPageResponse<T> {
    private String message;
    private Long nextCursor;
    private boolean hasNext;
    private T data;

    public CursorPageResponse(String message, Long nextCursor, boolean hasNext, T data) {
        this.message = message;
        this.nextCursor = nextCursor;
        this.hasNext = hasNext;
        this.data = data;
    }

    public static <T> CursorPageResponse<T> of(String message, Long nextCursor, boolean hasNext, T data) {
        return new CursorPageResponse<>(message, nextCursor, hasNext, data);
    }

    public static <T> CursorPageResponse<T> ok(Long nextCursor, boolean hasNext, T data) {
        return of("조회성공", nextCursor, hasNext, data);
    }
}
