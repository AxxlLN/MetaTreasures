package com.MetaTreasures.MetaTreasures.core.repositories;

import com.MetaTreasures.MetaTreasures.core.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("SELECT t FROM Transaction t WHERE t.user.userId = :userId ORDER BY t.createdAt DESC")
    List<Transaction> findByUserIdOrderByCreatedAtDesc(@Param("userId") Long userId);

    @Query("SELECT t FROM Transaction t " +
            "WHERE t.user.userId = :userId " +
            "AND (LOWER(t.token.name) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(t.token.symbol) LIKE LOWER(CONCAT('%', :query, '%'))) " +
            "ORDER BY t.createdAt DESC")
    List<Transaction> searchByUserIdAndToken(@Param("userId") Long userId,
                                             @Param("query") String query);

}
