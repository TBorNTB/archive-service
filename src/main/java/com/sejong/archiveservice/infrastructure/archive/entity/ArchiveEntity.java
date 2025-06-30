package com.sejong.archiveservice.infrastructure.archive.entity;

import com.sejong.archiveservice.infrastructure.archive.converter.ArchiveIDConverter;
import com.sejong.archiveservice.infrastructure.archive.embeddable.ContentEmbeddable;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "archive")
@Getter
@NoArgsConstructor
public class ArchiveEntity {
    @Id
    @Convert(converter = ArchiveIDConverter.class)
    @Column(name = "archive_id", columnDefinition = "VARCHAR(255)")
    private String id;

    @Embedded
    private ContentEmbeddable content;

    @Column(name = "thumbnail_path")
    private String thumbnailPath;

    @Column(name = "attached_file_path")
    private String attachedFilePath;

    @Column(name = "writer_id", nullable = false)
    private Long writerId;

    @ElementCollection
    @CollectionTable(name = "archive_participant_ids", joinColumns = @JoinColumn(name = "archive_id"))
    @Column(name = "archive_user_ids")
    private List<Long> participantIds = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "archive_tags", joinColumns = @JoinColumn(name = "archive_id"))
    @Column(name = "tag")
    private List<String> tags = new ArrayList<>();

    @Column(name = "likes", nullable = false)
    private int likes = 0;

    @Column(name = "view", nullable = false)
    private int view = 0;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;
}
