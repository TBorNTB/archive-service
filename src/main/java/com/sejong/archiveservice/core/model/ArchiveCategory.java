package com.sejong.archiveservice.core.model;

import java.util.Arrays;

public enum ArchiveCategory {

    MT("MT"),
    OT("OT"),
    STUDY("스터디"),
    SEMINAR("세미나"),
    UNITED_SEMINAR("연합 세미나"),
    CONFERENCE("컨퍼런스"),
    CTF("CTF"),
    ;

    private final String description;

    ArchiveCategory(String description) {
        this.description = description;
    }

    public static ArchiveCategory of(String description) {
        return Arrays.stream(ArchiveCategory.values())
                .filter(c -> c.description.equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
    }
}
