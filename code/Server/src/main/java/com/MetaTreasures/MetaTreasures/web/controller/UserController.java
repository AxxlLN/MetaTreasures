package com.MetaTreasures.MetaTreasures.web.controller;

import com.MetaTreasures.MetaTreasures.core.mapping.UserMapping;
import com.MetaTreasures.MetaTreasures.core.model.User;
import com.MetaTreasures.MetaTreasures.core.service.UserService;
import com.MetaTreasures.MetaTreasures.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapping userMapping;

    /**
     * Авторизация / регистрация пользователя через Firebase токен.
     */
    @PostMapping("/auth")
    public ResponseEntity<UserDto> authenticateUser(@RequestHeader("Authorization") String idToken) {
        if (idToken.startsWith("Bearer ")) idToken = idToken.substring(7);
        User user = userService.getOrCreateUser(idToken);
        return ResponseEntity.ok(userMapping.mapToDto(user));
    }

    /**
     * Получить данные текущего пользователя.
     */
    @GetMapping("/me")
    public ResponseEntity<UserDto> getCurrentUser(@RequestHeader("Authorization") String idToken) {
        if (idToken.startsWith("Bearer ")) idToken = idToken.substring(7);
        User user = userService.getCurrentUser(idToken);
        return ResponseEntity.ok(userMapping.mapToDto(user));
    }

//    @GetMapping
//    public List<UserDto> getAllUsers() {
//        return userService.getAllUsers().stream()
//                .map(UserDto::new)
//                .toList();
//    }
//
//    @PutMapping("/{id}")
//    public UserDto updateUser(@PathVariable Long id, @RequestBody UserDto dto) {
//        return new UserDto(userService.updateUser(id, dto));
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//    }
}
