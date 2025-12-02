package com.metatreasures.metatreasures.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.metatreasures.metatreasures.R

@Composable
fun TopSection() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxHeight(0.6f)
    ) {
        Image(
            painter = painterResource(R.drawable.my_logo_2),
            contentDescription = null,
            modifier = Modifier
                .size(300.dp)
                .padding(top = 48.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = stringResource(R.string.top_section_part1),
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = MaterialTheme.typography.headlineLarge.fontSize * 1.2f
            ),
            color = Color.Black
        )

        Text(
            text = stringResource(R.string.top_section_part2),
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = MaterialTheme.typography.headlineLarge.fontSize * 1.2f
            ),
            color = Color.Black
        )
    }
}
