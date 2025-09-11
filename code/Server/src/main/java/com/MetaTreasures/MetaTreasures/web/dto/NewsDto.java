package com.MetaTreasures.MetaTreasures.web.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewsDto {
    private String title;
    private String content;
    private String url;
    private String source;
    private LocalDateTime publishedAt;
    private String imageUrl;
}
