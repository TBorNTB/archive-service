package com.sejong.archiveservice.core.news;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Content {
    private String title;
    private String summary;
    private String content;
    private NewsCategory category;

    private Content(String title, String summary, String content, NewsCategory category) {
        validate(title, summary, content, category);
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.category = category;
    }

    public static Content of(String title, String summary, String content, NewsCategory category) {
        return new Content(title, summary, content, category);
    }

    private void validate(String title, String summary, String content, NewsCategory category) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("제목은 필수입니다.");
        }

        if (summary == null || summary.isEmpty()) {
            throw new IllegalArgumentException("요약은 필수입니다.");
        }

        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("내용은 필수입니다.");
        }

        if (category == null) {
            throw new IllegalArgumentException("카테고리는 필수입니다.");
        }
    }
}

