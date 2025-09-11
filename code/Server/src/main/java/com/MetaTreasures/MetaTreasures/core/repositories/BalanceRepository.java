package com.MetaTreasures.MetaTreasures.core.repositories;

import com.MetaTreasures.MetaTreasures.core.model.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
    List<Balance> findByUserUserId(Long userId);
}