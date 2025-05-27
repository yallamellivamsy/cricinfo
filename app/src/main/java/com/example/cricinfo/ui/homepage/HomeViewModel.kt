package com.example.cricinfo.ui.homepage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.cricinfo.domain.model.UserProfile
import com.example.cricinfo.domain.usecase.AuthUseCase
import com.example.cricinfo.ui.login.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {

    init {
        loadUserData()
    }
    // State for badge counts (one for each tab)
    var badgeCounts by mutableIntStateOf(3)

    private val _userData = MutableStateFlow<UserProfile?>(null)
    val userData: StateFlow<UserProfile?> = _userData

    // Simulate an API response updating the badge count
    fun fetchMessageCountFromApi() {
        viewModelScope.launch {
            delay(2000) // simulate network call
            badgeCounts = badgeCounts+1
        }
    }

    fun clearBadge(badgeCount: Int) {
            badgeCounts = 0
    }

    fun loadUserData() {
        viewModelScope.launch {

            val data = authUseCase.getUserData()  // suspend function returning UserProfile
            _userData.value = data
            Log.i("HomeViewModel", _userData.value.toString())
        }
    }
}
