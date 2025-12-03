package com.metatreasures.metatreasures.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.metatreasures.metatreasures.components.NewsLink
import com.metatreasures.metatreasures.viewmodel.NewsViewModel

private const val IMAGE_HEIGHT = 220
private const val DEFAULT_CONTENT_TEXT = "Нет текста"
private const val DEFAULT_NOT_FOUND_TEXT = "Новость не найдена"
private const val CLOSE_DESCRIPTION = "Закрыть"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetailScreen(
    navController: NavController,
    newsId: String?,
    viewModel: NewsViewModel = hiltViewModel()
) {
    val newsList by viewModel.news.collectAsState()
    val news = newsList.find { it.id == newsId?.toLongOrNull() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = CLOSE_DESCRIPTION
                        )
                    }
                }
            )
        },
        bottomBar = {}
    ) { innerPadding ->
        news?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(it.imageUrl),
                    contentDescription = it.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IMAGE_HEIGHT.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = it.title,
                            style = MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text = it.content ?: DEFAULT_CONTENT_TEXT,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                it.url?.let { url ->
                    NewsLink(url)
                }

                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Text(
                        text = it.publishedAt.orEmpty(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        } ?: Text(DEFAULT_NOT_FOUND_TEXT, modifier = Modifier.padding(16.dp))
    }
}
