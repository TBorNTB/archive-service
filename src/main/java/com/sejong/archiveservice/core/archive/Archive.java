package com.sejong.archiveservice.core.archive;

import com.sejong.archiveservice.core.ArchiveCategory;
import com.sejong.archiveservice.core.common.Filepath;
import java.time.LocalDate;
import java.util.List;

public class Archive {
    private String nickname;
    private String title;
    private Filepath thumbnailPath;
    private String summary;
    private Long writerId;
    private List<Long> participantIds;
    private String content; // TODO(sigmaith): List<Block>으로 바꿀지 고민.
    private LocalDate createdAt;
    private ArchiveCategory category;
    private List<String> tags;
    private Filepath attachedFilePath;
    private int likes;
    private int view;
}
