package com.metatreasures.metatreasures.data.dto

data class TransactionsDto(
    val txId: Long,
    val userId: Long,
    val tokenId: Long,
    val amount: Double,
    val type: String,
    val createdAt: String     
)