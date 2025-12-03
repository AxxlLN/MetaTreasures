package com.metatreasures.metatreasures.data

import android.util.Log
import com.metatreasures.metatreasures.data.dto.TokenDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenRepository @Inject constructor(
    private val client: OkHttpClient
) {
    suspend fun fetchTokens(): Result<List<TokenDto>> = withContext(Dispatchers.IO) {
        try {
            Log.d("TokenRepository", "Делаю GET запрос на /api/tokens")

            val request = Request.Builder()
                .url("http://192.168.100.13:8080/api/tokens")
                .get()
                .build()

            val response = client.newCall(request).execute()
            val body = response.body?.string()
                ?: return@withContext Result.failure(Exception("Empty response"))

            val jsonArray = JSONArray(body)
            val tokens = mutableListOf<TokenDto>()

            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                val metadataJson = JSONObject(obj.getString("metadata"))
                val metadata = metadataJson.keys().asSequence().associateWith { key ->
                    metadataJson.opt(key) ?: ""
                }

                tokens.add(
                    TokenDto(
                        tokenId = obj.getLong("tokenId"),
                        name = obj.getString("name"),
                        symbol = obj.getString("symbol"),
                        priceUsdt = obj.getDouble("priceUsdt"),
                        metadata = metadata
                    )
                )
            }

            Result.success(tokens)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
