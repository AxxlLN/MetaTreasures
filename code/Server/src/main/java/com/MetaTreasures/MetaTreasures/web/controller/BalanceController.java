package com.MetaTreasures.MetaTreasures.web.controller;

import com.MetaTreasures.MetaTreasures.core.service.BalanceService;
import com.MetaTreasures.MetaTreasures.web.dto.BalanceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/balances")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BalanceDto>> getUserBalances(@PathVariable Long userId) {
        return ResponseEntity.ok(balanceService.getUserBalances(userId));
    }

    @GetMapping("/user/{userId}/search")
    public ResponseEntity<List<BalanceDto>> searchUserTokens(
            @PathVariable Long userId,
            @RequestParam String query) {
        return ResponseEntity.ok(balanceService.searchUserTokens(userId, query));
    }

}
