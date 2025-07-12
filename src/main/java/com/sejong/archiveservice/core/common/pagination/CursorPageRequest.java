package com.sejong.archiveservice.core.common.pagination;

import java.util.Arrays;
import lombok.Getter;

@Getter
public class CursorPageRequest {
    Long cursor;
    int size;
    String sortBy;
    SortDirection direction;

    public enum SortDirection {
        ASC, DESC;

        public static CustomPageRequest.SortDirection from(String name) {
            return Arrays.stream(CustomPageRequest.SortDirection.values())
                    .filter(s -> s.name().equals(name))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("정렬 방향은 ASC/DESC 만 가능합니다."));
        }

    }

    private CursorPageRequest(Long cursor, int size, String sortBy, SortDirection direction) {
        this.cursor = cursor;
        this.size = size;
        this.sortBy = sortBy;
        this.direction = direction;
    }

    public static CursorPageRequest of(Long cursor, int size, String sortBy, SortDirection direction) {
        return new CursorPageRequest(cursor, size, sortBy, direction);
    }
}
