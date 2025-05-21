package com.example.cricinfo.ui.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItemInfo(val label: String, val icon: ImageVector)

data class NavigationItem(val label: String, val icon: ImageVector, val badgeCount: Int)
