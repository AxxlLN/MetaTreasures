package com.MetaTreasures.MetaTreasures.web.dto;

import com.MetaTreasures.MetaTreasures.core.model.User;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long userId;
    private String firebaseUid;
    private String email;
    private Instant createdAt;
    private Boolean verified;
    private List<BalanceDto> balances;
}
