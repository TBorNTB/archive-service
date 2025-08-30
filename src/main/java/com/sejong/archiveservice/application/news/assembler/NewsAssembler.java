package com.sejong.archiveservice.application.news.assembler;

import com.sejong.archiveservice.application.news.dto.NewsReqDto;
import com.sejong.archiveservice.core.news.Content;
import com.sejong.archiveservice.core.news.News;
import com.sejong.archiveservice.core.news.NewsCategory;
import com.sejong.archiveservice.core.user.UserId;
import com.sejong.archiveservice.core.user.UserIds;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NewsAssembler {
    public static News toNews(NewsReqDto reqDto) {
        Content content = Content.of(reqDto.title(), reqDto.summary(), reqDto.content(),
                NewsCategory.of(reqDto.category()));
        UserId userId = UserId.of(reqDto.writerId());
        UserIds userIds = UserIds.of(reqDto.participantIds());

        return News.create(content, userId, userIds, reqDto.tags());
    }

    public static Content toContent(NewsReqDto reqDto) {
        return Content.of(reqDto.title(), reqDto.summary(), reqDto.content(), NewsCategory.of(reqDto.category()));
    }
}
