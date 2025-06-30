package com.sejong.archiveservice.core.model;

import com.sejong.archiveservice.core.common.Filepath;
import com.sejong.archiveservice.core.util.ArchiveIDGenerator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Archive {
    private ArchiveID id;

    private Content content;

    private Filepath thumbnailPath;
    private List<Filepath> attachedFilePaths;

    private UserId writerId;
    private List<UserId> participantIds;
    private List<Tag> tags;

    private int likes;
    private int view;
    private LocalDate createdAt;

    @Builder(toBuilder = true)
    private Archive(ArchiveID id, Content content, Filepath thumbnailPath, List<Filepath> attachedFilePaths,
                    UserId writerId, List<UserId> participantIds, List<Tag> tags, int likes, int view, LocalDate createdAt) {
        this.id = id;
        this.content = content;
        this.thumbnailPath = thumbnailPath;
        this.attachedFilePaths = attachedFilePaths;
        this.writerId = writerId;
        this.participantIds = participantIds;
        this.tags = tags;
        this.likes = likes;
        this.view = view;
        this.createdAt = createdAt;
    }

    public static Archive of(ArchiveID id, Content content, Filepath thumbnailPath, List<Filepath> attachedFilePaths,
                             UserId writerId, List<UserId> participantIds, List<Tag> tags, int likes, int view, LocalDate createdAt) {
        return Archive.builder()
                .id(id)
                .content(content)
                .thumbnailPath(thumbnailPath)
                .attachedFilePaths(attachedFilePaths)
                .writerId(writerId)
                .participantIds(participantIds)
                .tags(tags)
                .likes(likes)
                .view(view)
                .createdAt(createdAt)
                .build();
    }

    public static Archive create(Content content, UserId writerId,
                                 List<UserId> participantIds, List<Tag> tags) {
        return Archive.builder()
                .id(ArchiveIDGenerator.generate())
                .content(content)
                .content(content)
                .thumbnailPath(null)
                .attachedFilePaths(new ArrayList<>())
                .writerId(writerId)
                .participantIds(participantIds)
                .tags(tags)
                .likes(0)
                .view(0)
                .createdAt(LocalDate.now())
                .build();
    }

    public Archive updateThumbnailPath(Filepath thumbnailPath) {
        return this.toBuilder()
                .thumbnailPath(thumbnailPath)
                .build();
    }

    public Archive updateAttachedFilePaths(Filepath filePath) {
        List<Filepath> newPaths = new ArrayList<>(this.attachedFilePaths);
        newPaths.add(filePath);
        return this.toBuilder()
                .attachedFilePaths(newPaths)
                .build();
    }
}