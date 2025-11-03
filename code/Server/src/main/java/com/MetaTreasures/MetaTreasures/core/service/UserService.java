package com.MetaTreasures.MetaTreasures.core.service;

import com.MetaTreasures.MetaTreasures.core.exeptions.serveciexeptions.UserNotFoundExeption;
import com.MetaTreasures.MetaTreasures.core.model.User;
import com.MetaTreasures.MetaTreasures.core.repositories.UserRepository;
import com.MetaTreasures.MetaTreasures.web.config.FirebaseAuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FirebaseAuthProvider firebaseAuthProvider;

    /**
     * Проверяет Firebase токен, если пользователя нет в БД — создаёт его.
     */
    public User getOrCreateUser(String idToken) throws UserNotFoundExeption {
        String firebaseUid = firebaseAuthProvider.verifyIdToken(idToken);
        boolean emailVerified = firebaseAuthProvider.isEmailVerified(idToken);

        return userRepository.findByFirebaseUid(firebaseUid)
                .map(user -> {
                    if (emailVerified && !user.getVerified()) {
                        user.setVerified(true);
                        userRepository.save(user);
                    }
                    return user;
                })
                .orElseGet(() -> {
                    User user = User.builder()
                            .firebaseUid(firebaseUid)
                            .email(null)
                            .createdAt(Instant.now())
                            .verified(emailVerified)
                            .build();
                    return userRepository.save(user);
                });
    }

    /**
     * Получает текущего пользователя по Firebase токену.
     */
    public User getCurrentUser(String idToken) throws UserNotFoundExeption {
        String firebaseUid = firebaseAuthProvider.verifyIdToken(idToken);
        return userRepository.findByFirebaseUid(firebaseUid)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Optional<User> getUserById(Long id) throws UserNotFoundExeption {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() throws UserNotFoundExeption {
        return userRepository.findAll();
    }
}
