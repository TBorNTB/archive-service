package com.sejong.archiveservice.application.archive.assembler;

import com.sejong.archiveservice.application.archive.dto.ArchiveReqDto;
import com.sejong.archiveservice.core.model.Archive;
import com.sejong.archiveservice.core.model.ArchiveCategory;
import com.sejong.archiveservice.core.model.Content;
import com.sejong.archiveservice.core.model.UserId;
import com.sejong.archiveservice.core.model.UserIds;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ArchiveAssembler {
    public static Archive toArchive(ArchiveReqDto reqDto) {
        Content content = Content.of(reqDto.title(), reqDto.summary(), reqDto.content(),
                ArchiveCategory.of(reqDto.category()));
        UserId userId = UserId.of(reqDto.writerId());
        UserIds userIds = UserIds.of(reqDto.participantIds());

        return Archive.create(content, userId, userIds, reqDto.tags());
    }
}
