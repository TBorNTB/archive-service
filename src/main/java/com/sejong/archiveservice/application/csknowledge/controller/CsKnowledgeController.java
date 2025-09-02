package com.sejong.archiveservice.application.csknowledge.controller;

import com.sejong.archiveservice.application.csknowledge.dto.CsKnowledgeReqDto;
import com.sejong.archiveservice.application.csknowledge.dto.CsKnowledgeResDto;
import com.sejong.archiveservice.application.csknowledge.service.CsKnowledgeService;
import com.sejong.archiveservice.application.pagination.CursorPageReqDto;
import com.sejong.archiveservice.application.pagination.OffsetPageReqDto;
import com.sejong.archiveservice.core.common.pagination.CursorPageResponse;
import com.sejong.archiveservice.core.common.pagination.OffsetPageResponse;
import com.sejong.archiveservice.core.csknowledge.CsKnowledge;
import com.sejong.archiveservice.core.csknowledge.TechCategory;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cs-knowledge")
@RequiredArgsConstructor
public class CsKnowledgeController {

    private final CsKnowledgeService csKnowledgeService;

    @PostMapping
    public ResponseEntity<CsKnowledgeResDto> createCsKnowledge(@Valid @RequestBody CsKnowledgeReqDto csKnowledgeReqDto) {
        CsKnowledge createdCsKnowledge = csKnowledgeService.createCsKnowledge(csKnowledgeReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(CsKnowledgeResDto.from(createdCsKnowledge));
    }

    @PutMapping("/{csKnowledgeId}")
    public ResponseEntity<CsKnowledgeResDto> updateCsKnowledge(
            @PathVariable Long csKnowledgeId,
            @Valid @RequestBody CsKnowledgeReqDto csKnowledgeReqDto) {
        CsKnowledge updatedCsKnowledge = csKnowledgeService.updateCsKnowledge(csKnowledgeId, csKnowledgeReqDto);
        return ResponseEntity.ok(CsKnowledgeResDto.from(updatedCsKnowledge));
    }

    @DeleteMapping("/{csKnowledgeId}")
    public ResponseEntity<Void> deleteCsKnowledge(@PathVariable Long csKnowledgeId) {
        csKnowledgeService.deleteCsKnowledge(csKnowledgeId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{csKnowledgeId}")
    public ResponseEntity<CsKnowledgeResDto> getCsKnowledge(@PathVariable Long csKnowledgeId) {
        CsKnowledge csKnowledge = csKnowledgeService.findById(csKnowledgeId);
        return ResponseEntity.ok(CsKnowledgeResDto.from(csKnowledge));
    }

    @GetMapping("/{csKnowledgeId}/exists")
    public ResponseEntity<Boolean> checkCsKnowledgeExists(@PathVariable Long csKnowledgeId) {
        Boolean exists = csKnowledgeService.exists(csKnowledgeId);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/category/{techCategory}")
    public ResponseEntity<List<CsKnowledgeResDto>> getCsKnowledgeByCategory(@PathVariable TechCategory techCategory) {
        List<CsKnowledge> csKnowledges = csKnowledgeService.findAllByTechCategory(techCategory);
        List<CsKnowledgeResDto> response = csKnowledges.stream()
                .map(CsKnowledgeResDto::from)
                .toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/unsent")
    public ResponseEntity<CsKnowledgeResDto> getUnsentKnowledge(
            @RequestParam TechCategory categoryName,
            @RequestParam String email) {
        Optional<CsKnowledge> unsentKnowledge = csKnowledgeService.findUnsentKnowledge(categoryName, email);
        return unsentKnowledge
                .map(knowledge -> ResponseEntity.ok(CsKnowledgeResDto.from(knowledge)))
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/offset")
    public ResponseEntity<OffsetPageResponse<List<CsKnowledge>>> getOffsetCsKnowledge(
            @Valid OffsetPageReqDto offsetPageReqDto) {
        OffsetPageResponse<List<CsKnowledge>> offsetCsKnowledge = csKnowledgeService.getOffsetCsKnowledge(offsetPageReqDto);
        return ResponseEntity.ok(offsetCsKnowledge);
    }

    @GetMapping("/cursor")
    public ResponseEntity<CursorPageResponse<List<CsKnowledge>>> getCursorCsKnowledge(
            @Valid CursorPageReqDto cursorPageReqDto) {
        CursorPageResponse<List<CsKnowledge>> cursorCsKnowledge = csKnowledgeService.getCursorCsKnowledge(cursorPageReqDto);
        return ResponseEntity.ok(cursorCsKnowledge);
    }
}