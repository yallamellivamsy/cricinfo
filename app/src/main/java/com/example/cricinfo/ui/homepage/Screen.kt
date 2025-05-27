package com.example.cricinfo.ui.homepage

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Mail
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val label: String, val icon: ImageVector, val badgeCount: Int) {
    object Home : Screen("home", "Home", Icons.Default.Home, 0)
    object Messages : Screen("messages", "Favorites", Icons.Rounded.Favorite, 2)
    object Search: Screen("search", "search", Icons.Filled.Search, 0)
    object Cart: Screen("cart", "cart", Icons.Filled.ShoppingCart, 3)
    object Profile : Screen("profile", "Profile", Icons.Default.Person, 0)

    companion object {
        val all = listOf(Home, Messages, Cart, Profile)
    }
}
