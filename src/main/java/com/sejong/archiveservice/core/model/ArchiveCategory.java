package com.sejong.archiveservice.core.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
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

    @JsonCreator
    public static ArchiveCategory of(String description) {
        return Arrays.stream(ArchiveCategory.values())
                .filter(c -> c.description.equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다"));
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}
