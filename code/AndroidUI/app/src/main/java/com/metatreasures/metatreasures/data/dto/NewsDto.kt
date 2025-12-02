package com.metatreasures.metatreasures.data.dto

data class NewsDto(
    val title: String,
    val content: String,
    val url: String,
    val source: String,
    val publishedAt: String,  // LocalDateTime → String
    val imageUrl: String
)