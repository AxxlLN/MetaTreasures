package com.metatreasures.metatreasures.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.metatreasures.metatreasures.R

@Composable
fun TokenTableHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                shape = RoundedCornerShape(dimensionResource(R.dimen.header_corner_radius))
            )
            .padding(dimensionResource(R.dimen.header_padding)),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(stringResource(R.string.column_name), fontWeight = FontWeight.Bold)
        Text(stringResource(R.string.column_price), fontWeight = FontWeight.Bold)
        Text(stringResource(R.string.column_change), fontWeight = FontWeight.Bold)
    }
}