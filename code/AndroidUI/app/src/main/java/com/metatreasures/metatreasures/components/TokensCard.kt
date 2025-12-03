package com.metatreasures.metatreasures.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.metatreasures.metatreasures.data.dto.TokenDto
import kotlin.collections.filter
import com.metatreasures.metatreasures.R

@Composable
fun TokensCard(
    visible: Boolean,
    tokens: List<TokenDto>,
    searchQuery: String,
    expanded: Boolean,
    onExpandToggle: () -> Unit,
    navController: NavController
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(600)) + expandVertically(),
        exit = fadeOut(tween(400)) + shrinkVertically()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.card_padding)),
            elevation = CardDefaults.cardElevation(
                defaultElevation = dimensionResource(R.dimen.card_elevation)
            ),
            shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.card_padding))
            ) {
                TokenTableHeader()

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_small)))

                val filteredTokens = tokens.filter { it.symbol.contains(searchQuery, ignoreCase = true) }
                val shownTokens = if (expanded) filteredTokens else filteredTokens.take(5)

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(shownTokens) { token ->
                        TokenCard(token, navController)
                    }
                }

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.spacing_small)))

                Text(
                    text = if (expanded) stringResource(R.string.collapse) else stringResource(R.string.expand),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .clickable { onExpandToggle() }
                        .padding(dimensionResource(R.dimen.spacing_small))
                )
            }
        }
    }
}
