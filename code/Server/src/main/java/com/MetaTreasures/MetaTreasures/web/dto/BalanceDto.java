package com.MetaTreasures.MetaTreasures.web.dto;

import com.MetaTreasures.MetaTreasures.core.model.Token;
import com.MetaTreasures.MetaTreasures.core.model.User;
import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceDto {
    private Long balanceId;
    private User userId;
    private Token tokenId;
    private String amount;
}
