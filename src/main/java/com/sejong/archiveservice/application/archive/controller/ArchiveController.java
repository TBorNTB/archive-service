package com.sejong.archiveservice.application.archive.controller;

import com.sejong.archiveservice.application.archive.dto.ArchiveReqDto;
import com.sejong.archiveservice.application.archive.dto.ArchiveResDto;
import com.sejong.archiveservice.application.archive.service.ArchiveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/archive")
@RequiredArgsConstructor
@Tag(name = "Archive", description = "아카이브 관련 API")
public class ArchiveController {
    private final ArchiveService archiveService;

    @PostMapping()
    public ResponseEntity<ArchiveResDto> create(@RequestBody ArchiveReqDto archiveReqDto) {

    }

    @GetMapping("/health")
    @Operation(summary = "헬스체크", description = "서비스 상태를 확인합니다.")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Archive Service is running!");
    }

    @GetMapping("/test")
    @Operation(summary = "테스트 엔드포인트", description = "Swagger 테스트용 엔드포인트입니다.")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test endpoint works!");
    }
}
