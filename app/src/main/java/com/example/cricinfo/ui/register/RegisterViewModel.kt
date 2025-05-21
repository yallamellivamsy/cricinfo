package com.example.cricinfo.ui.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cricinfo.domain.usecase.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<RegisterState>(RegisterState.Idle)
    val state: StateFlow<RegisterState> = _state

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = RegisterState.Loading
            _state.value = authUseCase.registerUseCase(email, password).fold(
                onSuccess = { RegisterState.Success(it) },
                onFailure = { RegisterState.Error(it.message ?: "Unknown error") }
            )
        }
    }
}