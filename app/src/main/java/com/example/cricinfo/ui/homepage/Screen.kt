package com.example.cricinfo.ui.homepage

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Mail
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector, val badgeCount: Int) {
    object Home : Screen("home", "Home", Icons.Default.Home, 0)
    object Messages : Screen("messages", "Messages", Icons.Rounded.Mail, 2)
    object Search: Screen("search", "search", Icons.Filled.Search, 0)
    object Cart: Screen("cart", "cart", Icons.Filled.Person, 0)
    object Profile : Screen("profile", "Profile", Icons.Default.Person, 0)

    companion object {
        val all = listOf(Home, Messages, Search, Cart, Profile)
    }
}
