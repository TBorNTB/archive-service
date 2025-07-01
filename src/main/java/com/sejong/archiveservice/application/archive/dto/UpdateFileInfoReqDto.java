package com.sejong.archiveservice.application.archive.dto;

import java.util.List;

public record UpdateFileInfoReqDto(
        String thumbnailPath,
        List<String> attachedFilePaths
) {
}
