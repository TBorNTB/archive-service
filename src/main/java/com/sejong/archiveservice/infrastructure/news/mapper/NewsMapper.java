package com.sejong.archiveservice.infrastructure.news.mapper;

import com.sejong.archiveservice.core.common.file.Filepath;
import com.sejong.archiveservice.core.news.Content;
import com.sejong.archiveservice.core.news.News;
import com.sejong.archiveservice.core.user.UserId;
import com.sejong.archiveservice.core.user.UserIds;
import com.sejong.archiveservice.infrastructure.news.embeddable.ContentEmbeddable;
import com.sejong.archiveservice.infrastructure.news.entity.NewsEntity;
import java.util.Arrays;
import java.util.List;

public class NewsMapper {
    public static News toDomain(NewsEntity newsEntity) {
        ContentEmbeddable contentEbd = newsEntity.getContent();
        Content content = Content.of(contentEbd.getTitle(),
                contentEbd.getSummary(),
                contentEbd.getContent(),
                contentEbd.getCategory());
        List<String> tags = Arrays.stream(newsEntity.getTags().split(",")).toList();

        return News.builder()
                .id(newsEntity.getId())
                .content(content)
                .thumbnailPath(Filepath.of(newsEntity.getThumbnailPath()))
                .writerId(UserId.of(newsEntity.getWriterId()))
                .participantIds(UserIds.of(newsEntity.getParticipantIds()))
                .tags(tags)
                .likes(newsEntity.getLikes())
                .view(newsEntity.getView())
                .createdAt(newsEntity.getCreatedAt())
                .build();
    }

    public static NewsEntity toEntity(News archive) {
        return NewsEntity.builder()
                .content(ContentEmbeddable.of(archive.getContent()))
                .thumbnailPath(archive.getThumbnailPath() == null ? null : archive.getThumbnailPath().path())
                .writerId(archive.getWriterId().userId())
                .participantIds(archive.getParticipantIds().toString())
                .tags(archive.getTags().toString())
                .createdAt(archive.getCreatedAt())
                .build();
    }
}
