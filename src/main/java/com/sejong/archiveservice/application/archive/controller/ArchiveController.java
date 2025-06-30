package com.sejong.archiveservice.application.archive.controller;

import com.sejong.archiveservice.application.archive.dto.ArchiveReqDto;
import com.sejong.archiveservice.application.archive.dto.ArchiveResDto;
import com.sejong.archiveservice.application.archive.service.ArchiveService;
import com.sejong.archiveservice.application.file.FileUploadRequest;
import com.sejong.archiveservice.application.file.FileUploader;
import com.sejong.archiveservice.application.file.PreSignedUrl;
import com.sejong.archiveservice.core.model.Archive;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final FileUploader fileUploader;

    @PostMapping()
    @Operation(summary = "아카이브 생성")
    public ResponseEntity<ArchiveResDto> createArchive(@RequestBody ArchiveReqDto archiveReqDto) {
        Archive archive = archiveService.create(archiveReqDto);
        return ResponseEntity.ok(ArchiveResDto.from(archive));
    }

    @PostMapping("/{archiveId}/files/presigned-url")
    @Operation(summary = "파일 업로드용 PreSigned URL 생성")
    public ResponseEntity<PreSignedUrl> preSignedUrl(
            @PathVariable("archiveId") Long archiveId,
            @RequestBody FileUploadRequest request) {

//        archiveService.validateArchiveExists(archiveId);

        PreSignedUrl preSignedUrl = fileUploader.generatePreSignedUrl(
                request.fileName(),
                request.contentType(),
                request.fileType()
        );

        return ResponseEntity.ok(preSignedUrl);
    }

//    @PatchMapping("/{archiveId}/files")
//    public ResponseEntity<Void> updateFileInfo(
//            @PathVariable Long id,
//            @RequestBody UpdateFileInfoRequest request) {
//
//        archiveService.updateFileInfo(id, request.getFilePath());
//        return ResponseEntity.ok().build();
//    }


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
