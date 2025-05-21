package com.example.cricinfo.ui.homepage

import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavWithBadges(tabNavController: NavHostController, badgeCount: Int) {

    NavigationBar {
        val currentDestination = tabNavController.currentBackStackEntryAsState().value?.destination

        Screen.all.forEach { screen ->
            NavigationBarItem(
                icon = {
                    BadgedBox(
                        badge = {
                            if (badgeCount > 0 && screen.route == "messages") {
                                Badge { Text(badgeCount.toString()) }
                            }
                        }
                    ) {
                        Icon(screen.icon, contentDescription = screen.label)
                    }
                },
                label = { Text(screen.label) },
                selected = currentDestination?.route == screen.route,
                onClick = {
                    tabNavController.navigate(screen.route) {
                        // Prevent multiple copies of same destination
                        launchSingleTop = true
                        // Restore state if needed
                        restoreState = true
                        // Pop to start destination to avoid deep stacks
                        popUpTo(tabNavController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                }
            )
        }
    }
}