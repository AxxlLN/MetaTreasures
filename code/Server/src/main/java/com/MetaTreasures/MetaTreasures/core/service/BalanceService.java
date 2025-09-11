package com.MetaTreasures.MetaTreasures.core.service;

import com.MetaTreasures.MetaTreasures.core.model.Balance;
import com.MetaTreasures.MetaTreasures.core.repositories.BalanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BalanceService {
    private final BalanceRepository balanceRepository;

    public List<Balance> getUserBalances(Long userId) {
        return balanceRepository.findByUserUserId(userId);
    }

    public Balance save(Balance balance) {
        return balanceRepository.save(balance);
    }
}
