package com.MetaTreasures.MetaTreasures.core.service;

import com.MetaTreasures.MetaTreasures.core.model.User;
import com.MetaTreasures.MetaTreasures.core.repositories.UserRepository;
import com.MetaTreasures.MetaTreasures.core.security.FirebaseAuthProvider;
import com.MetaTreasures.MetaTreasures.web.dto.UserDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final FirebaseAuthProvider firebaseAuthProvider;

    @Transactional
    public UserDto loginOrRegister(String idToken, String email) {
        String firebaseUid = firebaseAuthProvider.verifyIdToken(idToken);

        Optional<User> existing = userRepository.findByFirebaseUid(firebaseUid);
        if (existing.isPresent()) {
            User u = existing.get();
            if (!u.isVerified()) {
                throw new RuntimeException("User email not verified");
            }
            return new UserDto(u.getUserId(), u.getFirebaseUid(), u.getEmail(), u.getCreatedAt(), u.isVerified());
        }

        // Создаём нового пользователя
        User newUser = new User();
        newUser.setFirebaseUid(firebaseUid);
        newUser.setEmail(email);
        newUser.setVerified(firebaseAuthProvider.isEmailVerified(idToken));
        newUser.setCreatedAt(LocalDateTime.now());

        User saved = userRepository.save(newUser);
        return new UserDto(saved.getUserId(), saved.getFirebaseUid(), saved.getEmail(), saved.getCreatedAt(), saved.isVerified());
    }

    public UserDto getUserByFirebaseUid(String firebaseUid) {
        User u = userRepository.findByFirebaseUid(firebaseUid)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return new UserDto(u.getUserId(), u.getFirebaseUid(), u.getEmail(), u.getCreatedAt(), u.isVerified());
    }
    @Transactional
    public UserDto updateUser(Long userId, String newEmail) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setEmail(newEmail);
        User saved = userRepository.save(user);
        return new UserDto(saved.getUserId(), saved.getFirebaseUid(), saved.getEmail(),
                saved.getCreatedAt(), saved.isVerified());
    }

    public UserDto getCurrentUser(String idToken) {
        String firebaseUid = firebaseAuthProvider.verifyIdToken(idToken);
        return getUserByFirebaseUid(firebaseUid);
    }

    public boolean isEmailVerified(String idToken) {
        return firebaseAuthProvider.isEmailVerified(idToken);
    }

}
