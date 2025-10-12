package com.sejong.archiveservice.application.internal;

import com.sejong.archiveservice.application.csknowledge.service.CsKnowledgeService;
import com.sejong.archiveservice.application.news.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/archive")
public class NewsInternalController {

    private final NewsService newsService;
    private final CsKnowledgeService csKnowledgeService;

    @GetMapping("/check/{newsId}")
    @Operation(summary = "뉴스 존재 검증")
    public ResponseEntity<Boolean> checkNewsId(@PathVariable("newsId") Long newsId) {
        Boolean response = newsService.exists(newsId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/check/{csKnowledgeId}")
    @Operation(summary = "cs 지식 존재 검증")
    public ResponseEntity<Boolean> checkCSKnowledgeId(@PathVariable("csKnowledgeId") Long csKnowledgeId) {
      Boolean response = csKnowledgeService.exists(csKnowledgeId);
      return ResponseEntity.ok(response);
    }
}
