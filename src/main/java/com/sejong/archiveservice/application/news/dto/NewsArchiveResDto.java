package com.sejong.archiveservice.application.news.dto;

import com.sejong.archiveservice.core.news.News;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public record NewsArchiveResDto(
        Long id,
        String title,
        String summary,
        String content,
        String category,
        String thumbnailPath,
        String writerId,
        List<String> participantIds,
        List<String> tags,
        int likes,
        int view,
        LocalDate createdAt
) {
    public static NewsArchiveResDto from(News archive) {
        return new NewsArchiveResDto(
                archive.getId(),
                archive.getContent().getTitle(),
                archive.getContent().getSummary(),
                archive.getContent().getContent(),
                archive.getContent().getCategory().name(),
                archive.getThumbnailPath() != null ? archive.getThumbnailPath().path() : null,
                archive.getWriterId().userId(),
                extractUserIds(archive.getParticipantIds().toString()),
                archive.getTags(),
                archive.getLikes(),
                archive.getView(),
                archive.getCreatedAt()
        );
    }

    private static List<String> extractUserIds(String participantIds) {
        if (participantIds == null || participantIds.isEmpty()) {
            return List.of();
        }
        return Arrays.stream(participantIds.split(","))
                .toList();
    }
}