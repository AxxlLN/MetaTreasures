package com.MetaTreasures.MetaTreasures.core.mapping;

import com.MetaTreasures.MetaTreasures.core.model.User;
import com.MetaTreasures.MetaTreasures.web.dto.BalanceDto;
import com.MetaTreasures.MetaTreasures.web.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapping {

    /**
     * Маппинг User → UserDto с балансами.
     */

    public UserDto mapToDto(User user) {
        UserDto dto = new UserDto();
        dto.setUserId(user.getUserId());
        dto.setFirebaseUid(user.getFirebaseUid());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setVerified(user.getVerified());
        dto.setBalances(user.getBalances().stream()
                .map(balance -> new BalanceDto(
                        balance.getBalanceId(),
                        balance.getUser().getUserId(),
                        balance.getToken().getTokenId(),
                        balance.getAmount()
                ))
                .collect(Collectors.toList())
        );
        return dto;
    }
}
