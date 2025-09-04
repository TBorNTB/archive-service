package com.sejong.archiveservice.application.csknowledge.assembler;

import com.sejong.archiveservice.application.csknowledge.dto.CsKnowledgeReqDto;
import com.sejong.archiveservice.core.csknowledge.CsKnowledge;
import java.time.LocalDateTime;

public class CsKnowledgeAssembler {
    
    public static CsKnowledge toCsKnowledge(CsKnowledgeReqDto reqDto) {
        return CsKnowledge.builder()
                .title(reqDto.title())
                .content(reqDto.content())
                .category(reqDto.category())
                .createdAt(LocalDateTime.now())
                .build();
    }
    
    public static CsKnowledge toCsKnowledgeForUpdate(Long id, CsKnowledgeReqDto reqDto, LocalDateTime createdAt) {
        return CsKnowledge.builder()
                .id(id)
                .title(reqDto.title())
                .content(reqDto.content())
                .category(reqDto.category())
                .createdAt(createdAt)
                .build();
    }
}