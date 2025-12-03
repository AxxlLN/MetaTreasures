package com.metatreasures.metatreasures.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metatreasures.metatreasures.data.NewsRepository
import com.metatreasures.metatreasures.data.dto.NewsDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repo: NewsRepository
) : ViewModel() {

    private val _news = MutableStateFlow<List<NewsDto>>(emptyList())
    val news: StateFlow<List<NewsDto>> = _news

    init {
        startAutoRefresh()
    }

    fun loadNews() {
        viewModelScope.launch {
            val result = repo.fetchNews()
            result.onSuccess { list ->
                Log.d("NewsViewModel", "Получено ${list.size} новостей")
                _news.value = list
            }.onFailure { e ->
                Log.e("NewsViewModel", "Ошибка загрузки новостей", e)
            }
        }
    }

    private fun startAutoRefresh() {
        viewModelScope.launch {
            while (true) {
                loadNews()
                delay(60 * 60 * 1000L)
            }
        }
    }
}
