package com.MetaTreasures.MetaTreasures.core.service;

import com.MetaTreasures.MetaTreasures.core.mapping.BalanceMapping;
import com.MetaTreasures.MetaTreasures.core.model.Balance;
import com.MetaTreasures.MetaTreasures.core.repositories.BalanceRepository;
import com.MetaTreasures.MetaTreasures.web.dto.BalanceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final BalanceMapping balanceMapper;

    public List<BalanceDto> getUserBalances(Long userId) {
        return balanceRepository.findBalanceDtosByUserId(userId);
    }

    public List<BalanceDto> searchUserTokens(Long userId, String query) {
        return balanceRepository.searchUserTokens(userId, query);
    }

    public BalanceDto updateBalance(Balance balance) {
        Balance saved = balanceRepository.save(balance);
        return balanceMapper.toDto(saved);
    }

}