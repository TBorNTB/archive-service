package com.sejong.archiveservice.application.controller;

import com.sejong.archiveservice.application.service.ArchiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/archive")
@RequiredArgsConstructor
public class ArchiveController {
    private final ArchiveService archiveService;


}
