package com.metatreasures.metatreasures.navigation


import com.metatreasures.metatreasures.R

sealed class BottomNavItem(val route: String, val title: String, val icon: Int) {
    object NewsScreen : BottomNavItem("news", "Новости", R.drawable.ic_news)
    object TransactionScreen : BottomNavItem("transaction", "Транзакции", R.drawable.ic_transaction)
    object TokenScreen : BottomNavItem("token", "Токены", R.drawable.ic_main)
    object SettingsScreen : BottomNavItem("settings", "Настройки", R.drawable.ic_settings)
    object AccountScreen : BottomNavItem("account", "Аккаунт", R.drawable.ic_account)
}