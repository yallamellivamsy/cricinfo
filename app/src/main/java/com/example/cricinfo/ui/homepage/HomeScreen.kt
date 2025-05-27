package com.example.cricinfo.ui.homepage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.compose.*
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cricinfo.ui.homepage.cart.CartScreen
import com.example.cricinfo.ui.homepage.dashboard.HomePage
import com.example.cricinfo.ui.homepage.favorites.FavoritesScreen
import com.example.cricinfo.ui.homepage.profile.ProfileScreen
import com.example.cricinfo.ui.model.Product1
import com.example.cricinfo.ui.navigation.ProductNavHost

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = hiltViewModel()) {

    val tabNavController = rememberNavController()
    val badgeCount = homeViewModel.badgeCounts

    var sampleProducts = listOf(
        Product1(1, "HealthCare", 999.0, "https://via.placeholder.com/400x300"),
        Product1(2, "Beauty", 499.0, "https://via.placeholder.com/400x300"),
        Product1(3, "HealthCare", 1499.0, "https://via.placeholder.com/400x300")
    )

    var favorites by remember { mutableStateOf(sampleProducts) }

    val userData by homeViewModel.userData.collectAsState()
    Scaffold(
        bottomBar = {
            BottomNavWithBadges(tabNavController, badgeCount)
        }
    ) { innerPadding ->
        NavHost(
            navController = tabNavController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) { HomePage() }
            composable(Screen.Messages.route) { FavoritesScreen(favorites, onToggleFavorite = { product ->
                favorites = favorites.toMutableList().also {
                    if (it.contains(product)) it.remove(product)
                    else it.add(product)
                }
            }) }
            composable(Screen.Search.route) { ProductNavHost() }
            composable(Screen.Cart.route) { CartScreen() }
            composable(Screen.Profile.route) { ProfileScreen(navController, userData)
            }
        }
    }
}


@Composable
fun SearchScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    Text(
        text = "Hello Every One",
        modifier = Modifier
            .clickable {
                // Action when text is clicked
                homeViewModel.fetchMessageCountFromApi()
            }
            .padding(16.dp),  // Optional padding for better touch area
    )
}



