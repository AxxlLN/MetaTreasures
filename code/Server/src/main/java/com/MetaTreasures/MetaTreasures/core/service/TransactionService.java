package com.MetaTreasures.MetaTreasures.core.service;

import com.MetaTreasures.MetaTreasures.core.mapping.TransactionMapping;
import com.MetaTreasures.MetaTreasures.core.model.Transaction;
import com.MetaTreasures.MetaTreasures.core.repositories.TransactionRepository;
import com.MetaTreasures.MetaTreasures.web.dto.TransactionsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionMapping transactionMapper;

    public List<TransactionsDto> getUserTransactions(Long userId) {
        return transactionRepository.findByUserIdOrderByCreatedAtDesc(userId).stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());
    }

    public TransactionsDto addTransaction(Transaction tx) {
        tx.setCreatedAt(Instant.now());
        Transaction saved = transactionRepository.save(tx);
        return transactionMapper.toDto(saved);
    }

    public List<TransactionsDto> searchUserTransactions(Long userId, String tokenNameOrSymbol) {
        return transactionRepository.searchByUserIdAndToken(userId, tokenNameOrSymbol).stream()
                .map(transactionMapper::toDto)
                .collect(Collectors.toList());
    }

}
