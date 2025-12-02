package com.metatreasures.metatreasures.data.dto

data class UserDto(
    val userId: Long,
    val firebaseUid: String,
    val email: String?,
    val createdAt: String,
    val verified: Boolean,
    val balances: List<BalanceDto>
)