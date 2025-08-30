package com.sejong.archiveservice.core.csknowledge;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CsKnowledge {

    private Long id;
    private String title;
    private String content;
    private TechCategory category;
    private LocalDateTime createdAt;
}
