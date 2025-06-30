package com.sejong.archiveservice.core.model;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Content {
    private String title;
    private String summary;
    private String content;
    private ArchiveCategory category;

    private Content(String title, String summary, String content, ArchiveCategory category) {
        validate(title, summary, content, category);
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.category = category;
    }

    public static Content of(String title, String summary, String content, ArchiveCategory category) {
        return new Content(title, summary, content, category);
    }

    private void validate(String title, String summary, String content, ArchiveCategory category) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("title is null or empty");
        }

        if (summary == null || summary.isEmpty()) {
            throw new IllegalArgumentException("summary is null or empty");
        }

        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("content is null or empty");
        }

        if (category == null) {
            throw new IllegalArgumentException("category is null");
        }
    }
}

