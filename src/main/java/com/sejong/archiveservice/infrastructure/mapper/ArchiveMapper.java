package com.sejong.archiveservice.infrastructure.mapper;

import com.sejong.archiveservice.core.model.Archive;
import com.sejong.archiveservice.infrastructure.archive.entity.ArchiveEntity;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ArchiveMapper {
    public static ArchiveEntity toEntity(Archive archive) {
        // TODO: Archive -> ArchiveEntity 매핑 로직
        ArchiveEntity entity = new ArchiveEntity();
        // 필드 매핑...
        return entity;
    }

    public static Archive toDomain(ArchiveEntity entity) {
        // TODO: ArchiveEntity -> Archive 매핑 로직
        return Archive.builder()
                .id(entity.getId())
                // 다른 필드들...
                .build();
    }
}
