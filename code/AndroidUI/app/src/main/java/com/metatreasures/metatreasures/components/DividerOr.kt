package com.metatreasures.metatreasures.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.metatreasures.metatreasures.R

@Composable
fun DividerOr() {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.weight(1f))
        Text(text = stringResource(R.string.divider_or), modifier = Modifier.padding(horizontal = 8.dp))
        Divider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.weight(1f))
    }
}