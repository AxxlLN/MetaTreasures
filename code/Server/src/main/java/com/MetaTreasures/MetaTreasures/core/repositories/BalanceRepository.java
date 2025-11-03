package com.MetaTreasures.MetaTreasures.core.repositories;

import com.MetaTreasures.MetaTreasures.core.model.Balance;
import com.MetaTreasures.MetaTreasures.web.dto.BalanceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

    @Query("SELECT new com.MetaTreasures.MetaTreasures.web.dto.BalanceDto(" +
            "b.balanceId, b.user.userId, b.token.tokenId, b.amount) " +
            "FROM Balance b WHERE b.user.userId = :userId")
    List<BalanceDto> findBalanceDtosByUserId(@Param("userId") Long userId);

    @Query("SELECT new com.MetaTreasures.MetaTreasures.web.dto.BalanceDto(" +
            "b.balanceId, b.user.userId, b.token.tokenId, b.amount) " +
            "FROM Balance b " +
            "WHERE b.user.userId = :userId " +
            "AND (LOWER(b.token.name) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(b.token.symbol) LIKE LOWER(CONCAT('%', :query, '%')))")
    List<BalanceDto> searchUserTokens(@Param("userId") Long userId,
                                      @Param("query") String query);
}