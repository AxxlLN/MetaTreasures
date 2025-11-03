package com.MetaTreasures.MetaTreasures.core.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tokens")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    private String name;

    private String symbol;

    private Double priceUsdt;

    @Lob
    private String metadata;

    @OneToMany(mappedBy = "token", cascade = CascadeType.ALL)
    private List<Balance> balances;

    @OneToMany(mappedBy = "token", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}

