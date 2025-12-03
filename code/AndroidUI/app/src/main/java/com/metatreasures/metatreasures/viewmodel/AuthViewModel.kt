package com.metatreasures.metatreasures.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metatreasures.metatreasures.data.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repo: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state: StateFlow<AuthState> = _state

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    fun login() { _isLoggedIn.value = true }
    fun logout() { _isLoggedIn.value = false }

    fun register(email: String, password: String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(loading = true, error = null)

            repo.registerWithEmail(email, password)
                .onSuccess { token ->
                    Log.d("AuthViewModel", "Verification email has been sent to $email")
                    val user = repo.getFirebaseUser()
                    if (user != null && user.isEmailVerified) {
                        repo.sendTokenToServer(token ?: "")
                        _state.value = _state.value.copy(success = true, loading = false)
                    } else {
                        _state.value = _state.value.copy(
                            success = false,
                            loading = false,
                            error = "Please verify your email before logging in"
                        )
                    }
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

    fun onGoogleLoginSuccess(token: String) {
        viewModelScope.launch {
            if (!token.isNullOrEmpty()) {
                repo.saveToken(token)
                repo.sendTokenToServer(token)
                _isLoggedIn.value = true
                _state.value = _state.value.copy(success = true, loading = false)
            } else {
                _state.value = _state.value.copy(error = "Empty token", loading = false)
            }
        }
    }
    fun checkLoggedIn() {
        val token = repo.getSavedToken()
        val loggedIn = !token.isNullOrEmpty()
        Log.d("AuthViewModel", "checkLoggedIn: token=$token, loggedIn=$loggedIn")
        _isLoggedIn.value = loggedIn
        _state.value = _state.value.copy(success = loggedIn)
    }

    fun setLoading(isLoading: Boolean) {
        _state.value = _state.value.copy(loading = isLoading)
    }

    fun setError(message: String?) {
        _state.value = _state.value.copy(error = message, loading = false)
    }
}

data class AuthState(
    val loading: Boolean = false,
    val success: Boolean = false,
    val error: String? = null
)
