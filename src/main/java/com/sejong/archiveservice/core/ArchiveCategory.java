package com.sejong.archiveservice.core;

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

    public String getDescription() {
        return description;
    }
}
