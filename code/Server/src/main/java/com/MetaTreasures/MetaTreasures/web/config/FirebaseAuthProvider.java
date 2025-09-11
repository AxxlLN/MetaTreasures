package com.MetaTreasures.MetaTreasures.web.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.stereotype.Component;

@Component
public class FirebaseAuthProvider {

    /**
     * Проверяет токен Firebase и возвращает UID пользователя
     *
     * @param idToken ID-токен, который клиент прислал в Authorization header
     * @return firebaseUid
     */

    public String verifyIdToken(String idToken) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            return decodedToken.getUid();
        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Invalid Firebase ID token", e);
        }
    }

    public boolean isEmailVerified(String idToken) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            return decodedToken.isEmailVerified();
        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Invalid Firebase ID token", e);
        }
    }
}
