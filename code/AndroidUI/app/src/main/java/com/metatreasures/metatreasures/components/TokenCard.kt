package com.metatreasures.metatreasures.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.metatreasures.metatreasures.R
import com.metatreasures.metatreasures.data.dto.TokenDto

@Composable
fun TokenCard(token: TokenDto, navController: NavController) {
    val change = token.metadata["price_change_24h"]?.toString()?.toDoubleOrNull()
    val imageUrl = token.metadata["image"]?.toString()

    val cryptoInfoRoute = stringResource(R.string.crypto_info_route)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.spacing_small))
            .clickable {
                token.tokenId?.let { id ->
                    navController.navigate("$cryptoInfoRoute/$id")
                }
            },
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.token_card_elevation)),
        shape = RoundedCornerShape(dimensionResource(R.dimen.token_card_corner_radius))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.token_card_padding)),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (!imageUrl.isNullOrBlank()) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = token.symbol,
                        modifier = Modifier
                            .size(dimensionResource(R.dimen.token_icon_size))
                            .padding(end = dimensionResource(R.dimen.spacing_small))
                    )
                }
                Text(token.symbol, fontWeight = FontWeight.Bold)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text("$${token.priceUsdt}")
                Text(
                    text = "${change ?: "?"}%",
                    color = when {
                        change == null -> MaterialTheme.colorScheme.onSurface
                        change < 0 -> MaterialTheme.colorScheme.error
                        else -> MaterialTheme.colorScheme.secondary
                    },
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}
