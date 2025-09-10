package com.MetaTreasures.MetaTreasures.core.repositories;

import com.MetaTreasures.MetaTreasures.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByFirebaseUid(String firebaseUid);
    Optional<User> findByEmail(String email);
}

