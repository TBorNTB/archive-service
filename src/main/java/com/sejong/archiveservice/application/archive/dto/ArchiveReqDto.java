package com.sejong.archiveservice.application.archive.dto;

import java.util.List;

public record ArchiveReqDto(
    String title,
    String summary,
    String content,
    String category,
    Long writerId,
    List<Long> participantIds,
    List<String> tags
) {
}

// Filepath 제외
// likes, view 제외