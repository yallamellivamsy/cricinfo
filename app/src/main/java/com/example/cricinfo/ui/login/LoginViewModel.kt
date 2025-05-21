package com.example.cricinfo.ui.login

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.cricinfo.domain.usecase.AuthUseCase


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state: StateFlow<LoginState> = _state
    var email by mutableStateOf("")

    init {
        getRememberedEmail()
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = LoginState.Loading
            _state.value = authUseCase.loginUseCase(email, password).fold(
                onSuccess = { LoginState.Success(it) },
                onFailure = { LoginState.Error(it.message ?: "Unknown error") }
            )
        }
    }

    fun saveRememberedEmail(rememberMe: Boolean) {
        viewModelScope.launch {
            if(rememberMe){
                authUseCase.saveEmailUseCase(email)
            } else{
                authUseCase.clearEmailUseCase()
            }
        }
    }

    // This will be called when the app is initialized
    private fun getRememberedEmail() {
        viewModelScope.launch {
            authUseCase.getEmailUseCase()
                .collect { savedEmail ->
                    savedEmail?.let {
                        email = it
                    }
                }
        }
    }

    fun onEmailChanged(newEmail: String) {
        email = newEmail
    }

}
