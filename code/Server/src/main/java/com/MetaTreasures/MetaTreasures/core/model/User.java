package com.MetaTreasures.MetaTreasures.core.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firebaseUid;

    @Column(unique = true, nullable = false)
    private String email;

    private Instant createdAt;

    private Boolean verified;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Balance> balances;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}

