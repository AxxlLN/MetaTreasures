package com.MetaTreasures.MetaTreasures.web.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenDto {
    private Long tokenId;
    private String name;
    private String symbol;
    private Double priceUsdt;
    private String metadata;
}

