package com.MetaTreasures.MetaTreasures.web.dto;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDto {
    private Long balanceId;
    private Long userId;
    private Long tokenId;
    private String amount;
}
