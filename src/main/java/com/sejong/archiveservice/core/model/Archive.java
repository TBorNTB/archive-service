package com.sejong.archiveservice.core.model;

import com.sejong.archiveservice.core.common.Filepath;
import java.time.LocalDate;
import java.util.List;

public class Archive {
    private String title;

    private Filepath thumbnailPath;
    private Filepath attachedFilePath;

    private String summary;
    private String content; // TODO(sigmaith): List<Block>으로 바꿀지 고민.

    private Long writerId;
    private List<UserId> ids;

    private ArchiveCategory category;
    private List<String> tags;

    private int likes;
    private int view;

    private LocalDate createdAt;

}
