package com.metatreasures.metatreasures.data.dto

data class TokenDto(
    val tokenId: Long,
    val name: String,
    val symbol: String,
    val priceUsdt: Double,
    val metadata: Map<String, Any>
)
