package com.MetaTreasures.MetaTreasures.web.controller;

import com.MetaTreasures.MetaTreasures.core.model.News;
import com.MetaTreasures.MetaTreasures.core.service.NewsService;
import com.MetaTreasures.MetaTreasures.web.dto.NewsSummaryDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@AllArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public List<News> getLatestNews() {
        return newsService.getLatestNews();
    }

    @GetMapping("/summary")
    public List<NewsSummaryDto> getNewsSummary() {
        return newsService.getLatestNewsSummary();
    }
}