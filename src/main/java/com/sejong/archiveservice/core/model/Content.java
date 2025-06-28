package com.sejong.archiveservice.core.model;

import lombok.Builder;

public class Content {
    private String title;
    private String summary;
    private String content;
    private ArchiveCategory category;

    @Builder(toBuilder = true)
    private Content(String title, String summary, String content, ArchiveCategory category) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.category = category;
    }

    public static Content of(String title, String summary, String content, ArchiveCategory category) {
        return Content.builder()
                .title(title)
                .summary(summary)
                .content(content)
                .category(category)
                .build();
    }
}

