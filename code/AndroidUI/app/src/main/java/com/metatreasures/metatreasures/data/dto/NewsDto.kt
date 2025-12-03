package com.metatreasures.metatreasures.data.dto

data class NewsDto(
    val id: Long,
    val title: String,
    val content: String?,
    val url: String?,
    val source: String,
    val publishedAt: String?,
    val imageUrl: String?
)