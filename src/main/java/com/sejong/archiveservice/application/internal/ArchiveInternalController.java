package com.sejong.archiveservice.application.internal;

import com.sejong.archiveservice.application.archive.service.ArchiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/archive")
public class ArchiveInternalController {

    private final ArchiveService archiveService;

    @GetMapping("/check/{archiveId}")
    public ResponseEntity<Boolean> checkArchiveId(@PathVariable("archiveId") Long archiveId) {
        Boolean response = archiveService.exists(archiveId);
        return ResponseEntity.ok(response);
    }
}
