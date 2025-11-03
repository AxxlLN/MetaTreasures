package com.MetaTreasures.MetaTreasures.web.controller;

import com.MetaTreasures.MetaTreasures.core.service.TransactionService;
import com.MetaTreasures.MetaTreasures.web.dto.TransactionsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TransactionsDto>> getUserTransactions(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionService.getUserTransactions(userId));
    }

    @GetMapping("/user/{userId}/search")
    public ResponseEntity<List<TransactionsDto>> searchUserTransactions(
            @PathVariable Long userId,
            @RequestParam String query) {
        return ResponseEntity.ok(transactionService.searchUserTransactions(userId, query));
    }

    @PostMapping("/add")
    public ResponseEntity<TransactionsDto> addTransaction(@RequestBody TransactionsDto dto) {
        return ResponseEntity.ok(transactionService.addTransaction(null));
    }
}