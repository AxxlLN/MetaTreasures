package com.metatreasures.metatreasures.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metatreasures.metatreasures.data.TokenRepository
import com.metatreasures.metatreasures.data.dto.TokenDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TokenViewModel @Inject constructor(
    private val repo: TokenRepository
) : ViewModel() {

    private val _tokens = MutableStateFlow<List<TokenDto>>(emptyList())
    val tokens: StateFlow<List<TokenDto>> = _tokens

    init {
        startAutoRefresh()
    }

    fun loadTokens() {
        viewModelScope.launch {
            val result = repo.fetchTokens()
            result.onSuccess { list ->
                Log.d("TokenViewModel", "Получено ${list.size} токенов")
                _tokens.value = list
            }.onFailure { e ->
                Log.e("TokenViewModel", "Ошибка загрузки токенов", e)
            }
        }
    }

    private fun startAutoRefresh() {
        viewModelScope.launch {
            while (true) {
                loadTokens()
                kotlinx.coroutines.delay(60 * 60 * 1000L)
            }
        }
    }
}