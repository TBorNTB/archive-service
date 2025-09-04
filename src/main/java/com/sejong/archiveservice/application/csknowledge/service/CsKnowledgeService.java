package com.sejong.archiveservice.application.csknowledge.service;

import com.sejong.archiveservice.application.csknowledge.assembler.CsKnowledgeAssembler;
import com.sejong.archiveservice.application.csknowledge.dto.CsKnowledgeReqDto;
import com.sejong.archiveservice.application.pagination.CursorPageReqDto;
import com.sejong.archiveservice.application.pagination.OffsetPageReqDto;
import com.sejong.archiveservice.core.common.pagination.CursorPageRequest;
import com.sejong.archiveservice.core.common.pagination.CursorPageResponse;
import com.sejong.archiveservice.core.common.pagination.CustomPageRequest;
import com.sejong.archiveservice.core.common.pagination.OffsetPageResponse;
import com.sejong.archiveservice.core.csknowledge.CsKnowledge;
import com.sejong.archiveservice.core.csknowledge.CsKnowledgeRepository;
import com.sejong.archiveservice.core.csknowledge.TechCategory;
import com.sejong.archiveservice.infrastructure.csknowledge.kafka.CsKnowledgeEventPublisher;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CsKnowledgeService {

    private final CsKnowledgeRepository csKnowledgeRepository;
    private final CsKnowledgeEventPublisher csKnowledgeEventPublisher;

    @Transactional
    public CsKnowledge createCsKnowledge(CsKnowledgeReqDto csKnowledgeReqDto) {
        CsKnowledge csKnowledge = CsKnowledgeAssembler.toCsKnowledge(csKnowledgeReqDto);
        CsKnowledge savedCsKnowledge = csKnowledgeRepository.save(csKnowledge);
        csKnowledgeEventPublisher.publishCreated(savedCsKnowledge);
        return savedCsKnowledge;
    }

    @Transactional
    public CsKnowledge updateCsKnowledge(Long csKnowledgeId, CsKnowledgeReqDto csKnowledgeReqDto) {
        CsKnowledge existingKnowledge = csKnowledgeRepository.findById(csKnowledgeId);
        CsKnowledge updatedKnowledge = CsKnowledgeAssembler.toCsKnowledgeForUpdate(
                csKnowledgeId, 
                csKnowledgeReqDto, 
                existingKnowledge.getCreatedAt()
        );
        CsKnowledge savedKnowledge = csKnowledgeRepository.update(updatedKnowledge);
        csKnowledgeEventPublisher.publishUpdated(savedKnowledge);
        return savedKnowledge;
    }

    @Transactional
    public void deleteCsKnowledge(Long csKnowledgeId) {
        CsKnowledge csKnowledge = csKnowledgeRepository.findById(csKnowledgeId);
        csKnowledgeRepository.delete(csKnowledge);
        csKnowledgeEventPublisher.publishDeleted(csKnowledgeId);
    }

    public CsKnowledge findById(Long csKnowledgeId) {
        return csKnowledgeRepository.findById(csKnowledgeId);
    }

    public Boolean exists(Long csKnowledgeId) {
        return csKnowledgeRepository.existsById(csKnowledgeId);
    }

    public List<CsKnowledge> findAllByTechCategory(TechCategory techCategory) {
        return csKnowledgeRepository.findAllByTechCategory(techCategory);
    }

    public Optional<CsKnowledge> findUnsentKnowledge(TechCategory categoryName, String email) {
        return csKnowledgeRepository.findUnsentKnowledge(categoryName, email);
    }

    public OffsetPageResponse<List<CsKnowledge>> getOffsetCsKnowledge(OffsetPageReqDto offsetPageReqDto) {
        CustomPageRequest pageRequest = offsetPageReqDto.toPageRequest();
        return csKnowledgeRepository.findAllWithOffset(pageRequest);
    }

    public CursorPageResponse<List<CsKnowledge>> getCursorCsKnowledge(CursorPageReqDto cursorPageReqDto) {
        CursorPageRequest pageRequest = cursorPageReqDto.toPageRequest();
        return csKnowledgeRepository.findAllWithCursor(pageRequest);
    }
}