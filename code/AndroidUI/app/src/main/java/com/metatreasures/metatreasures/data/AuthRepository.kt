package com.metatreasures.metatreasures.data

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class AuthRepository(
    private val auth: FirebaseAuth,
    private val client: OkHttpClient = OkHttpClient()
) {

    suspend fun registerWithEmail(email: String, password: String): Result<String> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            val token = auth.currentUser?.getIdToken(true)?.await()?.token
            Result.success(token ?: "")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loginWithEmail(email: String, password: String): Result<String> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            val token = auth.currentUser?.getIdToken(true)?.await()?.token
            Result.success(token ?: "")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loginWithGoogle(idToken: String): Result<String> {
        return try {
            val credential = com.google.firebase.auth.GoogleAuthProvider.getCredential(idToken, null)
            auth.signInWithCredential(credential).await()
            val token = auth.currentUser?.getIdToken(true)?.await()?.token
            Result.success(token ?: "")
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun sendTokenToServer(token: String): Result<String> {
        return try {
            val request = Request.Builder()
                .url("http://192.168.100.13:8080/api/users/auth")
                .header("Authorization", "Bearer $token")
                .post("".toRequestBody("application/json".toMediaType()))
                .build()

            Log.d("AuthRepository", "Отправляю запрос на /api/users/auth с токеном: $token")

            val response = client.newCall(request).execute()
            val serverResponse = response.body?.string() ?: "empty"

            Log.d("AuthRepository", "Ответ сервера: $serverResponse")

            Result.success(serverResponse)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
