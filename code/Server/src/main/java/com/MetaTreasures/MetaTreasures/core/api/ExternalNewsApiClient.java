package com.MetaTreasures.MetaTreasures.core.api;

import com.MetaTreasures.MetaTreasures.web.dto.NewsDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Component
public class ExternalNewsApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${crypto.news.api.key}")
    private String apiKey;

    @Value("${news.api.url}")
    private String newsApiUrl;

    public List<NewsDto> fetchLatestNews() {
        String url = UriComponentsBuilder.fromHttpUrl(newsApiUrl)
                .queryParam("apikey", apiKey)
                .queryParam("q", "crypto")
                .queryParam("language", "en")
                .toUriString();

        Map<String, Object> response = restTemplate.getForObject(url, Map.class);
        if (response == null || !response.containsKey("results")) return List.of();

        List<Map<String, Object>> results = (List<Map<String, Object>>) response.get("results");
        List<NewsDto> newsList = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (Map<String, Object> item : results) {
            NewsDto news = new NewsDto();
            news.setTitle((String) item.get("title"));
            news.setContent((String) item.get("content"));
            news.setUrl((String) item.get("link"));
            news.setSource((String) item.get("source_id"));
            news.setImageUrl((String) item.get("image_url"));

            if (item.get("pubDate") != null) {
                String dateStr = (String) item.get("pubDate");
                news.setPublishedAt(LocalDateTime.parse(dateStr, formatter));
            } else {
                news.setPublishedAt(LocalDateTime.now());
            }

            newsList.add(news);
        }

        return newsList;
    }
}

