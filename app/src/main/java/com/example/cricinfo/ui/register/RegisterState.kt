package com.example.cricinfo.ui.register

import com.example.cricinfo.domain.model.User

sealed class RegisterState {
    object Idle : RegisterState()
    object Loading : RegisterState()
    data class Success(val user: User) : RegisterState()
    data class Error(val message: String) : RegisterState()
}