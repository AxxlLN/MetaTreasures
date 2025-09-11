package com.MetaTreasures.MetaTreasures.web.dto;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionsDto {
    private Long txId;
    private Long userId;
    private Long tokenId;
    private Double amount;
    private String type;
    private Instant createdAt;
}

