package com.metatreasures.metatreasures.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.metatreasures.metatreasures.R
import com.metatreasures.metatreasures.navigation.TokenNavItem

@Composable
fun TopPanel(navController: NavController, isLoggedIn: Boolean) {
    var isExchange by remember { mutableStateOf(true) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_language),
            contentDescription = "Смена языка",
            modifier = Modifier.size(24.dp)
        )

        ExchangeWalletToggle(
            isExchange = isExchange,
            onToggle = { toggled ->
                isExchange = toggled
                if (isExchange) {
                    navController.navigate(TokenNavItem.TokenWalletScreen.route)
                } else {
                    navController.navigate(TokenNavItem.TokenScreen.route)
                }
            }
        )

        Icon(
            painter = painterResource(R.drawable.my_logo),
            contentDescription = "App logo",
            modifier = Modifier.size(28.dp)
        )
    }
}