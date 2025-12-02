package com.metatreasures.metatreasures.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metatreasures.metatreasures.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repo: AuthRepository) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true, error = null)

            repo.registerWithEmail(email, password)
                .onSuccess { token ->
                    repo.sendTokenToServer(token ?: "")
                    _state.value = _state.value.copy(success = true, loading = false)
                }
                .onFailure { throwable ->
                    _state.value = _state.value.copy(error = throwable?.message, loading = false)
                }
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true, error = null)

            repo.loginWithEmail(email, password)
                .onSuccess { token ->
                    repo.sendTokenToServer(token ?: "")
                    _state.value = _state.value.copy(success = true, loading = false)
                }
                .onFailure { throwable ->
                    _state.value = _state.value.copy(error = throwable?.message, loading = false)
                }
        }
    }

    fun loginWithGoogle(idToken: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true, error = null)

            repo.loginWithGoogle(idToken)
                .onSuccess { token ->
                    repo.sendTokenToServer(token ?: "")
                    _state.value = _state.value.copy(success = true, loading = false)
                }
                .onFailure { throwable ->
                    _state.value = _state.value.copy(error = throwable?.message, loading = false)
                }
        }
    }
}

data class AuthState(
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)
