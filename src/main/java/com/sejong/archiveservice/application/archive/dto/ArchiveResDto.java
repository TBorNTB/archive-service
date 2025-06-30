package com.sejong.archiveservice.application.archive.dto;

import com.sejong.archiveservice.core.model.Archive;

public record ArchiveResDto(
        Archive archive
) {

    public static ArchiveResDto from(Archive archive) {
        return new ArchiveResDto(archive);
    }
}
