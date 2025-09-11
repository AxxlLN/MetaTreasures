package com.MetaTreasures.MetaTreasures.core.service;

import com.MetaTreasures.MetaTreasures.core.api.ExternalNewsApiClient;
import com.MetaTreasures.MetaTreasures.core.model.News;
import com.MetaTreasures.MetaTreasures.core.repositories.NewsRepository;
import com.MetaTreasures.MetaTreasures.web.dto.NewsDto;
import com.MetaTreasures.MetaTreasures.web.dto.NewsSummaryDto;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class NewsService {

    private final NewsRepository newsRepository;
    private final ExternalNewsApiClient apiClient;

    @PostConstruct
    public void loadInitialNews() {
        fetchAndSaveLatestNews();
    }

    @Scheduled(fixedRate = 10 * 60 * 1000)
    @Transactional
    public void scheduledNewsUpdate() {
        fetchAndSaveLatestNews();
    }

    @Transactional
    public void fetchAndSaveLatestNews() {
        List<NewsDto> latestNews = apiClient.fetchLatestNews();

        for (NewsDto dto : latestNews) {
            if (!newsRepository.existsByUrl(dto.getUrl())) {
                News news = new News();
                news.setTitle(dto.getTitle());
                news.setContent(dto.getContent());
                news.setUrl(dto.getUrl());
                news.setSource(dto.getSource());
                news.setPublishedAt(dto.getPublishedAt());
                news.setImageUrl(dto.getImageUrl());
                newsRepository.save(news);
            }
        }

        LocalDateTime sevenDaysAgo = LocalDateTime.now().minusDays(7);
        newsRepository.deleteByPublishedAtBefore(sevenDaysAgo);

        List<News> allNews = newsRepository.findAllByOrderByPublishedAtDesc();
        if (allNews.size() > 20) {
            List<News> oldNews = allNews.subList(20, allNews.size());
            newsRepository.deleteAll(oldNews);
        }
    }

    public List<News> getLatestNews() {
        return newsRepository.findTop20ByOrderByPublishedAtDesc();
    }

    public List<NewsSummaryDto> getLatestNewsSummary() {
        return getLatestNews().stream()
                .map(news -> new NewsSummaryDto(
                        news.getTitle(),
                        news.getSource(),
                        news.getImageUrl()
                ))
                .collect(Collectors.toList());
    }
}
