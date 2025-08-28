package com.sejong.archiveservice.core.news;

import com.sejong.archiveservice.core.common.file.Filepath;
import com.sejong.archiveservice.core.user.UserId;
import com.sejong.archiveservice.core.user.UserIds;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class News {

    private Long id;
    private Content content;
    private Filepath thumbnailPath;
    private UserId writerId;
    private UserIds participantIds;
    private List<String> tags;
    private int likes;
    private int view;
    private LocalDate createdAt;

    @Builder(toBuilder = true)
    private News(Long id, Content content, Filepath thumbnailPath,
                 UserId writerId, UserIds participantIds, List<String> tags, int likes, int view, LocalDate createdAt) {
        this.id = id;
        this.content = content;
        this.thumbnailPath = thumbnailPath;
        this.writerId = writerId;
        this.participantIds = participantIds;
        this.tags = tags;
        this.likes = likes;
        this.view = view;
        this.createdAt = createdAt;
    }

    public static News create(Content content, UserId writerId, UserIds participantIds, List<String> tags) {
        return News.builder()
                .id(null)
                .content(content)
                .thumbnailPath(null)
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

    public void updateFileInfo(Filepath filepath) {
        this.thumbnailPath = filepath;
    }
}