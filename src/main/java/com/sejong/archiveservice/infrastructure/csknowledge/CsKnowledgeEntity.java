package com.sejong.archiveservice.infrastructure.csknowledge;

import com.sejong.archiveservice.core.csknowledge.CsKnowledge;
import com.sejong.archiveservice.core.csknowledge.TechCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "cs_knowledge",
        indexes = {
                @Index(name = "idx_cs_knowledge_category_id", columnList = "categoryName, id")
        }
)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CsKnowledgeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoryName", columnDefinition = "VARCHAR(50)", nullable = false)
    private TechCategory techCategory;

    private LocalDateTime createdAt;

    public CsKnowledge toDomain() {
        return CsKnowledge.builder()
                .id(id)
                .title(title)
                .content(content)
                .category(techCategory)
                .createdAt(createdAt)
                .build();
    }

    public static CsKnowledgeEntity from(CsKnowledge knowledge) {
        return CsKnowledgeEntity.builder()
                .id(knowledge.getId())
                .title(knowledge.getTitle())
                .content(knowledge.getContent())
                .techCategory(knowledge.getCategory())
                .createdAt(knowledge.getCreatedAt())
                .build();
    }
}
