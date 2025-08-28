package com.sejong.archiveservice.application.news.controller;

import com.sejong.archiveservice.application.config.security.UserContext;
import com.sejong.archiveservice.application.file.FileUploadRequest;
import com.sejong.archiveservice.application.file.FileUploader;
import com.sejong.archiveservice.application.file.PreSignedUrl;
import com.sejong.archiveservice.application.news.dto.NewsArchiveResDto;
import com.sejong.archiveservice.application.news.dto.NewsReqDto;
import com.sejong.archiveservice.application.news.service.NewsService;
import com.sejong.archiveservice.application.pagination.CursorPageReqDto;
import com.sejong.archiveservice.application.pagination.OffsetPageReqDto;
import com.sejong.archiveservice.core.common.pagination.CursorPageResponse;
import com.sejong.archiveservice.core.common.pagination.OffsetPageResponse;
import com.sejong.archiveservice.core.news.News;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
@Tag(name = "News", description = "뉴스 관련 API")
public class NewsController {

    private final NewsService newsService;
    private final FileUploader fileUploader;

    @GetMapping("/health")
    public String healthCheck() {
        return "ok";
    }

    @PostMapping()
    @Operation(summary = "뉴스 생성")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<NewsArchiveResDto> createArchive(@RequestBody NewsReqDto newsReqDto) {
        UserContext currentUser = getCurrentUser();

        NewsReqDto updatedReqDto = new NewsReqDto(newsReqDto.title(),
                newsReqDto.summary(),
                newsReqDto.content(),
                newsReqDto.category(),
                currentUser.getUserId(),
                newsReqDto.participantIds(),
                newsReqDto.tags());

        News archive = newsService.create(updatedReqDto);
        return ResponseEntity.ok(NewsArchiveResDto.from(archive));
    }

    @PostMapping("/files/presigned-url")
    @Operation(summary = "파일 업로드용 PreSigned URL 생성")
//    @SecurityRequirement(name = "bearerAuth") TODO: 보안 설정
    public ResponseEntity<PreSignedUrl> preSignedUrl(@RequestBody FileUploadRequest request) {
        PreSignedUrl preSignedUrl = fileUploader.generatePreSignedUrl(
                request.fileName(),
                request.contentType(), // "image/jpeg"
                request.fileType() // "image"
        );

        return ResponseEntity.ok(preSignedUrl);
    }

    @GetMapping("/offset")
    @Operation(summary = "뉴스 조회 (오프셋 기반 페이지네이션)")
    public ResponseEntity<OffsetPageResponse<List<News>>> getOffsetArchives(@ModelAttribute @Valid OffsetPageReqDto offsetPageReqDto) {
        OffsetPageResponse<List<News>> offsetArchives = newsService.getOffsetArchives(offsetPageReqDto);
        return ResponseEntity.ok(offsetArchives);
    }

    @GetMapping("/cursor")
    @Operation(summary = "뉴스 조회 (커서 기반 페이지네이션)")
    public ResponseEntity<CursorPageResponse<List<News>>> getCursorArchives(@ModelAttribute @Valid CursorPageReqDto cursorPageReqDto) {
        CursorPageResponse<List<News>> cursorArchives = newsService.getCursorArchives(cursorPageReqDto);
        return ResponseEntity.ok(cursorArchives);
    }

    @GetMapping("/{archiveId}")
    @Operation(summary = "뉴스 조회")
    public ResponseEntity<NewsArchiveResDto> getArchive(@PathVariable Long archiveId) {
        News archive = newsService.findById(archiveId);
        return ResponseEntity.ok(NewsArchiveResDto.from(archive));
    }

    @PutMapping("/{archiveId}")
    @Operation(summary = "뉴스 수정")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<NewsArchiveResDto> updateArchive(@PathVariable Long archiveId, @RequestBody NewsReqDto newsReqDto) {
        String writerId = getCurrentUser().getUserId();
        News archive = newsService.updateArchive(archiveId, newsReqDto, writerId);
        return ResponseEntity.ok(NewsArchiveResDto.from(archive));
    }

    @DeleteMapping("/{archiveId}")
    @Operation(summary = "뉴스 삭제")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<Void> deleteArchive(@PathVariable Long archiveId) {
        String writerId = getCurrentUser().getUserId();
        newsService.deleteArchive(archiveId, writerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private UserContext getCurrentUser() {
        return (UserContext) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
    }
}
