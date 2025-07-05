package com.sejong.archiveservice.infrastructure.archive.embeddable;

import com.sejong.archiveservice.core.model.ArchiveCategory;
import com.sejong.archiveservice.core.model.Content;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
public class ContentEmbeddable {
    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String summary;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ArchiveCategory category;

    private ContentEmbeddable(String title, String summary, String content, ArchiveCategory category) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.category = category;
    }

    public static ContentEmbeddable of(Content content) {
        return new ContentEmbeddable(content.getTitle(), content.getSummary(), content.getContent(), content.getCategory());
    }
}
