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

    fun register(email: String, password: String, confirmPassword: String, name: String, mobileNumber: String, dob: String) {
        if(password != confirmPassword){
            _state.value = RegisterState.Error("Both Password and Confirm Password should be same!")
        } else{
            viewModelScope.launch {
                _state.value = RegisterState.Loading
                _state.value = authUseCase.registerUseCase(email, password, name, mobileNumber, dob).fold(
                    onSuccess = { RegisterState.Success(it) },
                    onFailure = { RegisterState.Error(it.message ?: "Unknown error") }
                )
            }
        }

    }
}