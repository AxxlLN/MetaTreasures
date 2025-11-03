package com.MetaTreasures.MetaTreasures.core.mapping;

import com.MetaTreasures.MetaTreasures.core.model.Balance;
import com.MetaTreasures.MetaTreasures.web.dto.BalanceDto;
import org.springframework.stereotype.Component;

@Component
public class BalanceMapping {

    public BalanceDto toDto(Balance balance) {
        if (balance == null) return null;
        return new BalanceDto(
                balance.getBalanceId(),
                balance.getUser().getUserId(),
                balance.getToken().getTokenId(),
                balance.getAmount()
        );
    }

    public Balance fromDto(BalanceDto dto, Balance balanceEntity) {
        if (balanceEntity == null) balanceEntity = new Balance();
        balanceEntity.setAmount(dto.getAmount());
        return balanceEntity;
    }

}
