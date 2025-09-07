package com.sejong.archiveservice.application.news.dto;

import com.sejong.archiveservice.core.news.News;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public record NewsResDto(
        Long id,
        String title,
        String summary,
        String content,
        String category,
        String thumbnailPath,
        String writerId,
        List<String> participantIds,
        List<String> tags,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static NewsResDto from(News archive) {
        return new NewsResDto(
                archive.getId(),
                archive.getContent().getTitle(),
                archive.getContent().getSummary(),
                archive.getContent().getContent(),
                archive.getContent().getCategory().name(),
                archive.getThumbnailPath() != null ? archive.getThumbnailPath().path() : null,
                archive.getWriterId().userId(),
                extractUserIds(archive.getParticipantIds().toString()),
                archive.getTags(),
                archive.getCreatedAt(),
                archive.getUpdatedAt()
        );
    }

    public static NewsResDto from(News archive, Map<String, String> usernamesMap) {
        return new NewsResDto(
                archive.getId(),
                archive.getContent().getTitle(),
                archive.getContent().getSummary(),
                archive.getContent().getContent(),
                archive.getContent().getCategory().name(),
                archive.getThumbnailPath() != null ? archive.getThumbnailPath().path() : null,
                usernamesMap.getOrDefault(archive.getWriterId().userId(), archive.getWriterId().userId()),
                archive.getParticipantIds().toList().stream()
                        .map(userId -> usernamesMap.getOrDefault(userId, userId))
                        .toList(),
                archive.getTags(),
                archive.getCreatedAt(),
                archive.getUpdatedAt()
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