package com.example.cricinfo.ui.homepage

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class HomeViewModel : ViewModel() {
    // State for badge counts (one for each tab)
    var badgeCounts by mutableIntStateOf(10)

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
}
