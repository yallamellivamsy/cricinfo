package com.example.cricinfo.ui.forgotpassword

import com.example.cricinfo.domain.model.User

sealed class ForgotState {
    object Idle : ForgotState()
    object Loading : ForgotState()
    data class Success(val user: User) : ForgotState()
    data class Error(val message: String) : ForgotState()
}