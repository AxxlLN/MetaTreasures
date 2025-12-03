package com.metatreasures.metatreasures.data

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.tasks.await
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val client: OkHttpClient,
    @ApplicationContext private val context: Context
) {

    private val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    suspend fun registerWithEmail(email: String, password: String): Result<String> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            val user = auth.currentUser

            user?.sendEmailVerification()?.await()

            val token = user?.getIdToken(true)?.await()?.token
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

    suspend fun loginWithGoogle(): Result<String> {
        return try {
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

    fun saveToken(token: String) {
        Log.d("AuthRepository", "Сохраняю токен: $token")
        prefs.edit().putString("auth_token", token).apply()
    }

    fun getSavedToken(): String? {
        val token = prefs.getString("auth_token", null)
        Log.d("AuthRepository", "Достал токен: $token")
        return token
    }

    fun clearSavedToken() {
        prefs.edit().remove("auth_token").apply()
    }

    fun getFirebaseUser(): FirebaseUser? {
        return auth.currentUser
    }
}
