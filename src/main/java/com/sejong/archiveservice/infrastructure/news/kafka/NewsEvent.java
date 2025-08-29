package com.sejong.archiveservice.infrastructure.news.kafka;

import com.sejong.archiveservice.core.common.file.Filepath;
import com.sejong.archiveservice.core.news.Content;
import com.sejong.archiveservice.core.news.News;
import com.sejong.archiveservice.core.user.UserId;
import com.sejong.archiveservice.core.user.UserIds;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewsEvent {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    private String id;
    private Content content;
    private Filepath thumbnailPath;
    private UserId writerId;
    private UserIds participantIds;
    private List<String> tags;
    private String createdAt;
    private String updatedAt;

    public static NewsEvent from(News news) {
        return NewsEvent.builder()
                .id(news.getId().toString())
                .content(news.getContent())
                .thumbnailPath(news.getThumbnailPath())
                .writerId(news.getWriterId())
                .participantIds(news.getParticipantIds())
                .tags(news.getTags())
                .createdAt(news.getCreatedAt().truncatedTo(ChronoUnit.MILLIS).format(FORMATTER))
                .updatedAt(news.getUpdatedAt().truncatedTo(ChronoUnit.MILLIS).format(FORMATTER))
                .build();
    }
}
