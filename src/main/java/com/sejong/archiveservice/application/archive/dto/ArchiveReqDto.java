package com.sejong.archiveservice.application.archive.dto;

import com.sejong.archiveservice.core.model.Content;
import com.sejong.archiveservice.core.model.Tag;
import com.sejong.archiveservice.core.model.UserId;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public record ArchiveReqDto(
    Content content,
    MultipartFile thumbnail, // TODO: 파일 저장은 따로
    MultipartFile attachedFile, // TODO: 파일 저장은 따로
    UserId writerId,
    List<UserId> participants,
    List<Tag> tags,
    int likes,
    int view
) {
}
