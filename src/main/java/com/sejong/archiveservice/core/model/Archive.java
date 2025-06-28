package com.sejong.archiveservice.core.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.sejong.archiveservice.core.common.Filepath;

public class Archive {
    private Long id;
    private String title;
    private Filepath thumbnailPath;
    private Filepath attachedFilePath;
    private String summary;
    private String content;
    private UserId writerId;
    private List<UserId> userIds;
    private ArchiveCategory category;
    private List<String> tags;
    private int likes;
    private int view;
    private LocalDate createdAt;

    public Archive() {
        this.userIds = new ArrayList<>();
        this.tags = new ArrayList<>();
        this.likes = 0;
        this.view = 0;
        this.createdAt = LocalDate.now();
    }

    public Archive(String title, UserId writerId, ArchiveCategory category) {
        this();
        this.title = title;
        this.writerId = writerId;
        this.category = category;
    }

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public Filepath getThumbnailPath() { return thumbnailPath; }
    public Filepath getAttachedFilePath() { return attachedFilePath; }
    public String getSummary() { return summary; }
    public String getContent() { return content; }
    public UserId getWriterId() { return writerId; }
    public List<UserId> getUserIds() { return userIds; }
    public ArchiveCategory getCategory() { return category; }
    public List<String> getTags() { return tags; }
    public int getLikes() { return likes; }
    public int getView() { return view; }
    public LocalDate getCreatedAt() { return createdAt; }

    // Business methods
    public void updateContent(String summary, String content) {
        this.summary = summary;
        this.content = content;
    }

    public void addTag(String tag) {
        if (!this.tags.contains(tag)) {
            this.tags.add(tag);
        }
    }

    public void removeTag(String tag) {
        this.tags.remove(tag);
    }

    public void incrementLikes() {
        this.likes++;
    }

    public void incrementView() {
        this.view++;
    }

    public void setThumbnailPath(Filepath thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public void setAttachedFilePath(Filepath attachedFilePath) {
        this.attachedFilePath = attachedFilePath;
    }
}