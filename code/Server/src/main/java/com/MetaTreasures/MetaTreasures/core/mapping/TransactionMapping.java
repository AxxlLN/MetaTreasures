package com.MetaTreasures.MetaTreasures.core.mapping;

import com.MetaTreasures.MetaTreasures.core.model.Transaction;
import com.MetaTreasures.MetaTreasures.web.dto.TransactionsDto;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapping {

    public TransactionsDto toDto(Transaction tx) {
        if (tx == null) return null;
        return new TransactionsDto(
                tx.getTxId(),
                tx.getUser().getUserId(),
                tx.getToken().getTokenId(),
                tx.getAmount(),
                tx.getType(),
                tx.getCreatedAt()
        );
    }

    public Transaction fromDto(TransactionsDto dto, Transaction txEntity) {
        if (txEntity == null) txEntity = new Transaction();
        txEntity.setAmount(dto.getAmount());
        txEntity.setType(dto.getType());
        return txEntity;
    }

}
