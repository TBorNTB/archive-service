package com.sejong.archiveservice.application.archive.dto;

import com.sejong.archiveservice.core.model.Archive;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public record ArchiveResDto(
        Long id,
        String title,
        String summary,
        String content,
        String category,
        String thumbnailPath,
        List<String> attachedFilePaths,
        String writerId,
        List<String> participantIds,
        List<String> tags,
        int likes,
        int view,
        LocalDate createdAt
) {
    public static ArchiveResDto from(Archive archive) {
        return new ArchiveResDto(
                archive.getId(),
                archive.getContent().getTitle(),
                archive.getContent().getSummary(),
                archive.getContent().getContent(),
                archive.getContent().getCategory().name(),
                archive.getThumbnailPath() != null ? archive.getThumbnailPath().path() : null,
                archive.getAttachedFilePaths() != null ?
                        List.of(archive.getAttachedFilePaths().toString().split(",")) : List.of(),
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