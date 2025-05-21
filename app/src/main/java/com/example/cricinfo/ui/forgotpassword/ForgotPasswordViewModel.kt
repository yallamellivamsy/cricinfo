package com.example.cricinfo.ui.forgotpassword

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.cricinfo.domain.usecase.AuthUseCase
import com.example.cricinfo.ui.register.RegisterState

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    var email by mutableStateOf("")
    var message by mutableStateOf("")
    var isLoading by mutableStateOf(false)

    fun onResetPasswordClick() {
        if (email.isBlank()) {
            message = "Please enter your email."
            return
        }

        viewModelScope.launch {
            isLoading = true
            val result = authUseCase.forgotPasswordUseCase(email)
            message = result.getOrElse { it.message ?: "Unknown error" }
            isLoading = false
        }
    }
}
