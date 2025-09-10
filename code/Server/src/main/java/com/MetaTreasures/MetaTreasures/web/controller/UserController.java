package com.MetaTreasures.MetaTreasures.web.controller;

import com.MetaTreasures.MetaTreasures.core.service.UserService;
import com.MetaTreasures.MetaTreasures.web.dto.UserDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public UserDto login(@RequestParam String idToken, @RequestParam String email) {
        return userService.loginOrRegister(idToken, email);
    }

    @GetMapping("/me")
    public UserDto getCurrentUser(@RequestHeader("Authorization") String idToken) {
        return userService.getCurrentUser(idToken);
    }

    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable Long userId, @RequestParam String email) {
        return userService.updateUser(userId, email);
    }

    @GetMapping("/verified")
    public boolean isEmailVerified(@RequestHeader("Authorization") String idToken) {
        return userService.isEmailVerified(idToken);
    }
}

