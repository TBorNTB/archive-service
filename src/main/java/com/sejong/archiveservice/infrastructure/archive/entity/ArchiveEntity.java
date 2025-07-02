package com.sejong.archiveservice.infrastructure.archive.entity;

import com.sejong.archiveservice.infrastructure.archive.converter.FilepathsConverter;
import com.sejong.archiveservice.infrastructure.archive.embeddable.ContentEmbeddable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "archive")
@Getter
@NoArgsConstructor
public class ArchiveEntity {

    @Id
    @Column(name = "archive_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private ContentEmbeddable content;

    @Column(name = "thumbnail_path")
    private String thumbnailPath;

    @Column(name = "attached_file_path")
    @Convert(converter = FilepathsConverter.class)
    private String attachedFilePath;

    @Column(name = "writer_id", nullable = false)
    private String writerId;


    @Column(name = "archive_user_ids")
    private String participantIds;


    @Column(name = "tag")
    private String tags;

    @Column(name = "likes", nullable = false)
    private int likes = 0;

    @Column(name = "view", nullable = false)
    private int view = 0;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Builder
    private ArchiveEntity(ContentEmbeddable content, String thumbnailPath, String attachedFilePath,
                          String writerId, String participantIds, String tags, int likes, int view, LocalDate createdAt) {
        this.content = content;
        this.thumbnailPath = thumbnailPath;
        this.attachedFilePath = attachedFilePath;
        this.writerId = writerId;
        this.participantIds = participantIds;
        this.tags = tags;
        this.likes = likes;
        this.view = view;
        this.createdAt = createdAt;
    }
}
