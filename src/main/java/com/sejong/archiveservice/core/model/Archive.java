package com.sejong.archiveservice.core.model;

import com.sejong.archiveservice.core.common.Filepath;
import com.sejong.archiveservice.core.common.Filepaths;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class Archive {

    private Long id;
    private Content content;
    private Filepath thumbnailPath;
    private Filepaths attachedFilePaths;
    private UserId writerId;
    private UserIds participantIds;
    private List<String> tags;
    private int likes;
    private int view;
    private LocalDate createdAt;

    @Builder(toBuilder = true)
    private Archive(Long id, Content content, Filepath thumbnailPath, Filepaths attachedFilePaths,
                    UserId writerId, UserIds participantIds, List<String> tags, int likes, int view, LocalDate createdAt) {
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

    public static Archive create(Content content, UserId writerId, UserIds participantIds, List<String> tags) {
        return Archive.builder()
                .id(null)
                .content(content)
                .thumbnailPath(null)
                .attachedFilePaths(null)
                .writerId(writerId)
                .participantIds(participantIds)
                .tags(tags)
                .likes(0)
                .view(0)
                .createdAt(LocalDate.now())
                .build();
    }

    public void update(Content content, UserIds participantIds, List<String> tags) {
        this.content = content;
        this.participantIds = participantIds;
        this.tags = tags;
    }

    public void updateFileInfo(Filepath filepath, Filepaths filepaths) {
        this.thumbnailPath = filepath;
        this.attachedFilePaths = filepaths;
    }
}