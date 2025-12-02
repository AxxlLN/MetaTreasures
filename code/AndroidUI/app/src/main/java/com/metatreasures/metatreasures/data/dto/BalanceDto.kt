package com.metatreasures.metatreasures.data.dto

data class BalanceDto(
    val balanceId: Long,
    val userId: Long,
    val tokenId: Long,
    val amount: String
)