package com.MetaTreasures.MetaTreasures.core.service;

import com.MetaTreasures.MetaTreasures.core.api.TokenApiParser;
import com.MetaTreasures.MetaTreasures.core.mapping.TokenMapping;
import com.MetaTreasures.MetaTreasures.core.model.Token;
import com.MetaTreasures.MetaTreasures.core.repositories.TokenRepository;
import com.MetaTreasures.MetaTreasures.web.dto.TokenDto;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;
    private final TokenApiParser tokenApiParser;
    private final TokenMapping tokenMapping;
    private final ObjectMapper objectMapper = new ObjectMapper();

    /** Загружаем токены при старте приложения */
    @PostConstruct
    public void loadTokensOnStartup() {
        updateTokensFromApi();
    }

    /** Обновляем токены раз в 30 минут */
    @Scheduled(fixedRate = 30 * 60 * 1000)
    public void scheduledTokenUpdate() {
        updateTokensFromApi();
    }

    /**
     * Возвращает список всех токенов из БД.
     */
    public List<TokenDto> getAllTokens() {
        return tokenRepository.findAll().stream()
                .map(tokenMapping::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Возвращает токен по символу (например, BTC, ETH).
     */
    public Optional<TokenDto> getTokenBySymbol(String symbol) {
        return tokenRepository.findBySymbol(symbol.toUpperCase())
                .map(tokenMapping::toDto);
    }

    /**
     * Обновляет или добавляет токены из CoinGecko API.
     */
    public void updateTokensFromApi() {
        List<Map<String, Object>> apiTokens = tokenApiParser.fetchTokensFromApi();

        for (Map<String, Object> coin : apiTokens) {
            try {
                String name = (String) coin.get("name");
                String symbol = ((String) coin.get("symbol")).toUpperCase();
                Double price = ((Number) coin.get("current_price")).doubleValue();

                Map<String, Object> meta = new HashMap<>();
                meta.put("image", coin.get("image"));
                meta.put("market_cap_rank", coin.get("market_cap_rank"));
                meta.put("price_change_24h", coin.get("price_change_percentage_24h"));
                String metadata = objectMapper.writeValueAsString(meta);

                Token token = tokenRepository.findBySymbol(symbol)
                        .orElse(Token.builder()
                                .symbol(symbol)
                                .name(name)
                                .build());

                token.setPriceUsdt(price);
                token.setMetadata(metadata);

                tokenRepository.save(token);
            } catch (Exception e) {
                log.error("Failed: {}", e.getMessage());
            }
        }

        log.info("Ok");
    }
}