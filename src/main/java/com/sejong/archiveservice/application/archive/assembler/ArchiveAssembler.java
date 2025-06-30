package com.sejong.archiveservice.application.archive.assembler;

import com.sejong.archiveservice.application.archive.dto.ArchiveReqDto;
import com.sejong.archiveservice.core.model.Archive;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ArchiveAssembler {
    public static Archive toArchive(ArchiveReqDto reqDto) {
        return Archive.create(reqDto.content(), reqDto.writerId(), reqDto.participantIds(), reqDto.tags());
    }
}
