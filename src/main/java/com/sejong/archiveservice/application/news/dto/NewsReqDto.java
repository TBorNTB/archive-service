package com.sejong.archiveservice.application.news.dto;

import java.util.List;

public record NewsReqDto(
    String title,
    String summary,
    String content,
    String category,
    String writerId,
    List<String> participantIds,
    List<String> tags
) {
}
