package com.sejong.archiveservice.infrastructure.archive.mapper;

import com.sejong.archiveservice.core.common.file.Filepath;
import com.sejong.archiveservice.core.model.Archive;
import com.sejong.archiveservice.core.model.Content;
import com.sejong.archiveservice.core.model.UserId;
import com.sejong.archiveservice.core.model.UserIds;
import com.sejong.archiveservice.infrastructure.archive.embeddable.ContentEmbeddable;
import com.sejong.archiveservice.infrastructure.archive.entity.ArchiveEntity;
import java.util.Arrays;
import java.util.List;

public class ArchiveMapper {
    public static Archive toDomain(ArchiveEntity archiveEntity) {
        ContentEmbeddable contentEbd = archiveEntity.getContent();
        Content content = Content.of(contentEbd.getTitle(),
                contentEbd.getSummary(),
                contentEbd.getContent(),
                contentEbd.getCategory());
        List<String> tags = Arrays.stream(archiveEntity.getTags().split(",")).toList();

        return Archive.builder()
                .id(archiveEntity.getId())
                .content(content)
                .thumbnailPath(Filepath.of(archiveEntity.getThumbnailPath()))
                .writerId(UserId.of(archiveEntity.getWriterId()))
                .participantIds(UserIds.of(archiveEntity.getParticipantIds()))
                .tags(tags)
                .likes(archiveEntity.getLikes())
                .view(archiveEntity.getView())
                .createdAt(archiveEntity.getCreatedAt())
                .build();
    }

    public static ArchiveEntity toEntity(Archive archive) {
        return ArchiveEntity.builder()
                .content(ContentEmbeddable.of(archive.getContent()))
                .thumbnailPath(archive.getThumbnailPath() == null ? null : archive.getThumbnailPath().path())
                .writerId(archive.getWriterId().userId())
                .participantIds(archive.getParticipantIds().toString())
                .tags(archive.getTags().toString())
                .createdAt(archive.getCreatedAt())
                .build();
    }
}
