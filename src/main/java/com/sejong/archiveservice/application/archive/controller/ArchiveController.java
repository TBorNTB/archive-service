package com.sejong.archiveservice.application.archive.controller;

import com.sejong.archiveservice.application.archive.dto.ArchiveReqDto;
import com.sejong.archiveservice.application.archive.dto.ArchiveResDto;
import com.sejong.archiveservice.application.archive.dto.UpdateFileInfoReqDto;
import com.sejong.archiveservice.application.archive.service.ArchiveService;
import com.sejong.archiveservice.application.config.security.UserContext;
import com.sejong.archiveservice.application.file.FileUploadRequest;
import com.sejong.archiveservice.application.file.FileUploader;
import com.sejong.archiveservice.application.file.PreSignedUrl;
import com.sejong.archiveservice.core.model.Archive;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        UserContext currentUser = getCurrentUser();

        ArchiveReqDto updatedReqDto = new ArchiveReqDto(archiveReqDto.title(),
                archiveReqDto.summary(),
                archiveReqDto.content(),
                archiveReqDto.category(),
                currentUser.getUserId(),
                archiveReqDto.participantIds(),
                archiveReqDto.tags());

        Archive archive = archiveService.create(updatedReqDto);
        return ResponseEntity.ok(ArchiveResDto.from(archive));
    }

    @PostMapping("/files/presigned-url")
    @Operation(summary = "파일 업로드용 PreSigned URL 생성")
    public ResponseEntity<PreSignedUrl> preSignedUrl(@RequestBody FileUploadRequest request) {

//        archiveService.validateArchiveExists(archiveId);

        PreSignedUrl preSignedUrl = fileUploader.generatePreSignedUrl(
                request.fileName(),
                request.contentType(), // "image/jpeg"
                request.fileType() // "image"
        );

        return ResponseEntity.ok(preSignedUrl);
    }

    @PatchMapping("/{archiveId}/files")
    @Operation(summary = "파일 업로드 후 파일 경로 업데이트")
    public ResponseEntity<Void> updateFileInfo(
            @PathVariable Long archiveId,
            @RequestBody UpdateFileInfoReqDto request) {

        archiveService.updateFileInfo(archiveId, request);
        return ResponseEntity.ok().build();
    }

    // Todo: 커서 기반 페이지네이션 구현

    @GetMapping("/{archiveId}")
    @Operation(summary = "아카이브 조회")
    public ResponseEntity<ArchiveResDto> getArchive(@PathVariable Long archiveId) {
        Archive archive = archiveService.findById(archiveId);
        return ResponseEntity.ok(ArchiveResDto.from(archive));
    }

    // Todo: 요청을 보낸 유저가 곧 writer 인지 검증
    @PutMapping("/{archiveId}")
    @Operation(summary = "아카이브 수정")
    public ResponseEntity<ArchiveResDto> updateArchive(@PathVariable Long archiveId, @RequestBody ArchiveReqDto archiveReqDto) {
        String writerId = getCurrentUser().getUserId();
        Archive archive = archiveService.updateArchive(archiveId, archiveReqDto, writerId);
        return ResponseEntity.ok(ArchiveResDto.from(archive));
    }

    @DeleteMapping("/{archiveId}")
    @Operation(summary = "아카이브 삭제")
    public ResponseEntity<?> deleteArchive(@PathVariable Long archiveId) {
        String writerId = getCurrentUser().getUserId();
        archiveService.deleteArchive(archiveId, writerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private UserContext getCurrentUser() {
        return (UserContext) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }
}
