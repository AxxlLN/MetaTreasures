package com.metatreasures.metatreasures.data

import android.util.Log
import com.metatreasures.metatreasures.data.dto.NewsDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val client: OkHttpClient
) {
    suspend fun fetchNews(): Result<List<NewsDto>> = withContext(Dispatchers.IO) {
        try {
            Log.d("NewsRepository", "GET /api/news")

            val request = Request.Builder()
                .url("http://192.168.100.13:8080/api/news")
                .get()
                .build()

            val response = client.newCall(request).execute()
            val body = response.body?.string()
                ?: return@withContext Result.failure(Exception("Empty response"))

            val jsonArray = JSONArray(body)
            val newsList = mutableListOf<NewsDto>()

            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                Log.d("NewsRepository", "Новость ${obj.getString("title")} id=${obj.optLong("id", 0)}")
                newsList.add(
                    NewsDto(
                        id = i.toLong(),
                        title = obj.getString("title"),
                        content = obj.optString("content", null),
                        url = obj.optString("url", null),
                        source = obj.getString("source"),
                        publishedAt = obj.optString("publishedAt", null),
                        imageUrl = obj.optString("imageUrl", null)
                    )
                )
            }

            Result.success(newsList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
