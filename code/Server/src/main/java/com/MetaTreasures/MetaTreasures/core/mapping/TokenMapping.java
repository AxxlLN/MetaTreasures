package com.MetaTreasures.MetaTreasures.core.mapping;

import com.MetaTreasures.MetaTreasures.core.model.Token;
import com.MetaTreasures.MetaTreasures.web.dto.TokenDto;
import org.springframework.stereotype.Component;

@Component
public class TokenMapping {

    public TokenDto toDto(Token token) {
        return new TokenDto(
                token.getTokenId(),
                token.getName(),
                token.getSymbol(),
                token.getPriceUsdt(),
                token.getMetadata()
        );
    }
}
