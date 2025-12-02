package com.metatreasures.metatreasures.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.metatreasures.metatreasures.R
import com.metatreasures.metatreasures.components.GradientBackground
import com.metatreasures.metatreasures.navigation.Navigation
import com.metatreasures.metatreasures.viewmodel.TokenViewModel

@Composable
fun TokenScreen(
    navController: NavController,
    tokenViewModel: TokenViewModel,
    isLoggedIn: Boolean = false
) {
    var visible by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val tokens by tokenViewModel.tokens.collectAsState()

    LaunchedEffect(Unit) {
        visible = true
        tokenViewModel.loadTokens()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(12.dp)
        ) {
            // 🔹 Верхняя панель
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_language),
                    contentDescription = "Сменить язык",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { /* TODO: смена языка */ }
                )

                Button(
                    onClick = {
                        if (isLoggedIn) {
                            navController.navigate(Navigation.SignUpScreen.route)
                        } else {
                            navController.navigate(Navigation.LogInScreen.route)
                        }
                    }
                ) {
                    Text("Биржа → Кошелёк")
                }

                Icon(
                    painter = painterResource(R.drawable.my_logo),
                    contentDescription = "Приложение",
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 🔹 Поисковая строка
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Поиск токенов") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            // 🔹 Если пользователь не вошёл
            if (!isLoggedIn) {
                Text(
                    text = "Исследуй мир цифровых активов!",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Button(
                    onClick = { navController.navigate(Navigation.SignUpScreen.route) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(12.dp), // 🔹 меньшее закругление
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6A1B9A) // 🔹 фиолетовый фон
                    )
                ) {
                    Text(
                        text = "Регистрация / Логин",
                        color = Color.White // 🔹 чёрный текст
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // 🔹 Контейнер для списка токенов
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(tween(600)) + expandVertically(),
                exit = fadeOut(tween(400)) + shrinkVertically()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.95f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(12.dp)
                ) {
                    // 🔹 Шапка таблицы
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Название", fontWeight = FontWeight.Bold)
                        Text("Цена", fontWeight = FontWeight.Bold)
                        Text("Изм. 24ч", fontWeight = FontWeight.Bold)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    val filteredTokens =
                        tokens.filter { it.symbol.contains(searchQuery, ignoreCase = true) }
                    val shownTokens = if (expanded) filteredTokens else filteredTokens.take(5)

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                    ) {
                        items(shownTokens) { token ->
                            val change =
                                token.metadata["price_change_24h"]?.toString()?.toDoubleOrNull()
                            val imageUrl = token.metadata["image"]?.toString()

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp, horizontal = 8.dp)
                                    .clickable {
                                        token.tokenId?.let {
                                            navController.navigate("crypto_info/$it")
                                        }
                                    },
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    if (!imageUrl.isNullOrBlank()) {
                                        AsyncImage(
                                            model = imageUrl,
                                            contentDescription = token.symbol,
                                            modifier = Modifier
                                                .size(24.dp)
                                                .padding(end = 8.dp)
                                        )
                                    }
                                    Text(token.symbol, fontWeight = FontWeight.Bold)
                                }
                                Text("$${token.priceUsdt}")
                                Text(
                                    text = "${change ?: "?"}%",
                                    color = if (change != null && change < 0) Color.Red else Color.Green
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // 🔹 Общий текст "Подробнее" внизу
                    Text(
                        text = if (expanded) "Свернуть" else "Подробнее",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clickable { expanded = !expanded }
                            .padding(8.dp)
                    )
                }
            }
        }
    }
    GradientBackground()
}