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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cricinfo.ui.navigation.ProductNavHost

@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel = viewModel()) {

    val tabNavController = rememberNavController()
    val badgeCount = homeViewModel.badgeCounts

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
            composable(Screen.Messages.route) { ProfileScreen() }
            composable(Screen.Search.route) { ProductNavHost() }
            composable(Screen.Cart.route) { ProfileScreen() }
            composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}


@Composable
fun HomePage() {
    Text("Home Screen")
}

@Composable
fun SearchScreen(homeViewModel: HomeViewModel) {
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

@Composable
fun ProfileScreen() {
    Text("Profile Screen")
}
