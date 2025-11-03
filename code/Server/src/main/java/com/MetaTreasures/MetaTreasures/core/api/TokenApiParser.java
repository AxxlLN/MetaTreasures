package com.MetaTreasures.MetaTreasures.core.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Slf4j
@Component
public class TokenApiParser {

    @Value("${token.api.url}")
    private static String API_URL;

    private final RestTemplate restTemplate = new RestTemplate();

    public List<Map<String, Object>> fetchTokensFromApi() {
        try {
            ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                    API_URL,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {}
            );

            List<Map<String, Object>> coins = response.getBody();
            if (coins == null) {
                log.warn("API вернул пустое тело");
                return Collections.emptyList();
            }

            log.info("Alright", coins.size());
            return coins;

        } catch (Exception e) {
            log.error("Failed {}", e.getMessage());
            return Collections.emptyList();
        }
    }
}
