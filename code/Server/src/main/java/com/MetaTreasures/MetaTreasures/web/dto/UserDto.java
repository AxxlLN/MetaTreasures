package com.MetaTreasures.MetaTreasures.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private String firebaseUid;
    private String email;
    private LocalDateTime createdAt;
    private boolean verified;

}
