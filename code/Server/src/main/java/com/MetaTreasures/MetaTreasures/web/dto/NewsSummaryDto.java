package com.MetaTreasures.MetaTreasures.web.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsSummaryDto {
    private String title;
    private String source;
    private String imageUrl;
}
