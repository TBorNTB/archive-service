package com.sejong.archiveservice.infrastructure.entity;

import com.sejong.archiveservice.core.model.ArchiveCategory;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(name = "thumbnail_path")
    private String thumbnailPath;

    @Column(name = "attached_file_path")
    private String attachedFilePath;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @Column(columnDefinition = "TEXT")
    private String content; // TODO(sigmaith): List<Block>으로 바꿀지 고민.

    @Column(name = "writer_id", nullable = false)
    private Long writerId;

    @ElementCollection
    @CollectionTable(name = "archive_user_ids", joinColumns = @JoinColumn(name = "archive_id"))
    @Column(name = "archive_user_ids")
    private List<Long> userIds = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ArchiveCategory category;

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
