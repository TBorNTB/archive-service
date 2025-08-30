package com.sejong.archiveservice.application.news.service;

import com.sejong.archiveservice.application.news.assembler.NewsAssembler;
import com.sejong.archiveservice.application.news.dto.NewsReqDto;
import com.sejong.archiveservice.application.pagination.CursorPageReqDto;
import com.sejong.archiveservice.application.pagination.OffsetPageReqDto;
import com.sejong.archiveservice.core.common.pagination.CursorPageRequest;
import com.sejong.archiveservice.core.common.pagination.CursorPageResponse;
import com.sejong.archiveservice.core.common.pagination.CustomPageRequest;
import com.sejong.archiveservice.core.common.pagination.OffsetPageResponse;
import com.sejong.archiveservice.core.news.News;
import com.sejong.archiveservice.core.news.NewsRepository;
import com.sejong.archiveservice.core.user.UserId;
import com.sejong.archiveservice.core.user.UserIds;
import com.sejong.archiveservice.infrastructure.news.kafka.NewsEventPublisher;
import com.sejong.archiveservice.infrastructure.user.UserServiceClient;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NewsService {

    private final NewsRepository newsRepository;
    private final UserServiceClient userServiceClient;
    private final NewsEventPublisher newsEventPublisher;

    @Transactional
    public News createNews(NewsReqDto newsReqDto) {
//        validateUserExistence(newsReqDto.writerId(), newsReqDto.participantIds());

        News news = NewsAssembler.toNews(newsReqDto);
        News savedNews = newsRepository.save(news);
        newsEventPublisher.publishCreated(savedNews);
        return savedNews;
    }

    private void validateUserExistence(String writerId, List<String> participantIds) {
        if (!userServiceClient.exists(writerId)) {
            throw new IllegalArgumentException("존재하지 않는 작성자입니다.");
        }

        if (!userServiceClient.existsUsers(participantIds)) {
            throw new IllegalArgumentException("존재하지 않는 참여자가 있습니다.");
        }
    }

    @Transactional
    public News updateNews(Long newsId, NewsReqDto newsReqDto, String writerId) {
        News news = newsRepository.findBy(newsId);
        if (!news.getWriterId().equals(UserId.of(writerId))) {
            throw new IllegalArgumentException("뉴스 작성자만 내용을 수정할 수 있습니다.");
        }
        news.update(NewsAssembler.toContent(newsReqDto),
                UserIds.of(newsReqDto.participantIds()),
                newsReqDto.tags());
        News updatedNews = newsRepository.update(news);
        newsEventPublisher.publishUpdated(updatedNews);
        return updatedNews;
    }

    @Transactional
    public void deleteNews(Long newsId, String writerId) {
        News news = newsRepository.findBy(newsId);
        if (!news.getWriterId().equals(UserId.of(writerId))) {
            throw new IllegalArgumentException("뉴스 작성자만 내용을 삭제할 수 있습니다.");
        }
        newsRepository.delete(news);
        newsEventPublisher.publishDeleted(newsId);
    }

    public News findById(Long newsId) {
        return newsRepository.findBy(newsId);
    }

    public OffsetPageResponse<List<News>> getOffsetNews(OffsetPageReqDto offsetPageReqDto) {
        CustomPageRequest pageRequest = offsetPageReqDto.toPageRequest();
        return newsRepository.findAllWithOffset(pageRequest);
    }

    public CursorPageResponse<List<News>> getCursorNews(CursorPageReqDto cursorPageReqDto) {
        CursorPageRequest pageRequest = cursorPageReqDto.toPageRequest();
        return newsRepository.findAllWithCursor(pageRequest);
    }

    @Transactional(readOnly = true)
    public Boolean exists(Long newsId) {
        return newsRepository.existsNews(newsId);
    }
}
