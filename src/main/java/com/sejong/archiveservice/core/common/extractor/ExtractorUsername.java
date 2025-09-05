package com.sejong.archiveservice.core.common.extractor;

import com.sejong.archiveservice.core.csknowledge.CsKnowledge;
import com.sejong.archiveservice.core.news.News;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ExtractorUsername {

    public static List<String> FromKnowledges(List<CsKnowledge> knowledges) {
        return knowledges.stream()
                .map(knowledge -> knowledge.getWriterId().userId())
                .toList();
    }

    public static List<String> FromKnowledge(CsKnowledge knowledge) {
        return List.of(knowledge.getWriterId().userId());
    }

    public static List<String> FromNewses(List<News> newses) {
        return newses.stream()
                .map(news -> news.getWriterId().userId())
                .toList();
    }

    public static List<String> FromNews(News news) {
        return List.of(news.getWriterId().userId());
    }
}