package com.sejong.archiveservice.core.common;

import java.util.Arrays;
import lombok.Getter;

@Getter
public class CustomPageRequest {
    private final int page;
    private final int size;
    private final String sortBy;
    private final SortDirection direction;

    public enum SortDirection {
        ASC, DESC;

        public static SortDirection from(String name) {
            return Arrays.stream(SortDirection.values())
                    .filter(s -> s.name().equals(name))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("정렬 방향은 ASC/DESC 만 가능합니다."));
        }
    }

    private CustomPageRequest(int page, int size, String sortBy, SortDirection direction) {
        this.page = page;
        this.size = size;
        this.sortBy = sortBy;
        this.direction = direction;
    }

    public static CustomPageRequest of (int page, int size, String sortBy, SortDirection direction) {
        return new CustomPageRequest(page, size, sortBy, direction);
    }
}