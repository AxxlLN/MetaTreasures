package com.MetaTreasures.MetaTreasures.core.repositories;

import com.MetaTreasures.MetaTreasures.core.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByPublishedAtBefore(LocalDateTime dateTime);

    void deleteByPublishedAtBefore(LocalDateTime dateTime);

    List<News> findAllByOrderByPublishedAtDesc();

    List<News> findTop20ByOrderByPublishedAtDesc();

    boolean existsByUrl(String url);
}
