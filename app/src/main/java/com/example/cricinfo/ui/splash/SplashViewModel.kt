package com.example.cricinfo.ui.splash

import androidx.lifecycle.ViewModel
import com.example.cricinfo.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
):ViewModel() {

    fun isUserLoggedIn(): Boolean {
        return authUseCase.isUserLoggedInUseCase()
    }

}