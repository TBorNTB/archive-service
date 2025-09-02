package com.sejong.archiveservice.application.csknowledge.dto;

import com.sejong.archiveservice.core.csknowledge.CsKnowledge;
import com.sejong.archiveservice.core.csknowledge.TechCategory;
import java.time.LocalDateTime;

public record CsKnowledgeResDto(
        Long id,
        String title,
        String content,
        TechCategory category,
        LocalDateTime createdAt
) {
    public static CsKnowledgeResDto from(CsKnowledge csKnowledge) {
        return new CsKnowledgeResDto(
                csKnowledge.getId(),
                csKnowledge.getTitle(),
                csKnowledge.getContent(),
                csKnowledge.getCategory(),
                csKnowledge.getCreatedAt()
        );
    }
}