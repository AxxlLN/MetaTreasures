package com.MetaTreasures.MetaTreasures.web.controller;

import com.MetaTreasures.MetaTreasures.core.model.Token;
import com.MetaTreasures.MetaTreasures.core.service.TokenService;
import com.MetaTreasures.MetaTreasures.web.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tokens")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;

    /**
     * Получить список всех токенов.
     */
    @GetMapping
    public ResponseEntity<List<TokenDto>> getAllTokens() {
        return ResponseEntity.ok(tokenService.getAllTokens());
    }

    /**
     * Получить токен по символу (например /api/tokens/BTC).
     */
    @GetMapping("/{symbol}")
    public ResponseEntity<TokenDto> getTokenBySymbol(@PathVariable String symbol) {
        return tokenService.getTokenBySymbol(symbol)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
