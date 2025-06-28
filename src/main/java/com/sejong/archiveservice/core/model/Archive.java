package com.sejong.archiveservice.core.model;

import com.sejong.archiveservice.core.util.ArchiveIDGenerator;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sejong.archiveservice.core.common.Filepath;
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
    private Filepath attachedFilePath;

    private UserId writerId;
    private List<UserId> userIds;
    private List<Tag> tags;

    private int likes;
    private int view;
    private LocalDate createdAt;

    @Builder(toBuilder = true)
    private Archive(ArchiveID id, Content content, Filepath thumbnailPath, Filepath attachedFilePath,
                    UserId writerId, List<UserId> userIds, List<Tag> tags, int likes, int view, LocalDate createdAt) {
        this.id = id;
        this.content = content;
        this.thumbnailPath = thumbnailPath;
        this.attachedFilePath = attachedFilePath;
        this.writerId = writerId;
        this.userIds = userIds;
        this.tags = tags;
        this.likes = likes;
        this.view = view;
        this.createdAt = createdAt;
    }

    public static Archive of(ArchiveID id, Content content, Filepath thumbnailPath, Filepath attachedFilePath,
                             UserId writerId, List<UserId> userIds, List<Tag> tags, int likes, int view, LocalDate createdAt) {
        return Archive.builder()
                .id(id)
                .content(content)
                .thumbnailPath(thumbnailPath)
                .attachedFilePath(attachedFilePath)
                .writerId(writerId)
                .userIds(userIds)
                .tags(tags)
                .likes(likes)
                .view(view)
                .createdAt(createdAt)
                .build();
    }

    public static Archive create(Content content, Filepath thumbnailPath, Filepath attachedFilePath,
                                 UserId writerId, List<UserId> userIds, List<Tag> tags, int likes, int view) {
        return Archive.builder()
                .id(ArchiveIDGenerator.generate())
                .content(content)
                .content(content)
                .thumbnailPath(thumbnailPath)
                .attachedFilePath(attachedFilePath)
                .writerId(writerId)
                .userIds(userIds)
                .tags(tags)
                .likes(0)
                .view(0)
                .createdAt(LocalDate.now())
                .build();
    }

}