package com.example.cricinfo.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cricinfo.ui.forgotpassword.ForgotPasswordScreen
import com.example.cricinfo.ui.homepage.HomeScreen
import com.example.cricinfo.ui.login.LoginScreen
import com.example.cricinfo.ui.register.RegisterScreen
import com.example.cricinfo.ui.splash.SplashScreen


@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable("home") {
            HomeScreen(navController = navController)
        }
        composable("login") {
            LoginScreen (onLoginSuccess = {user -> navController.navigate("home") {
                popUpTo("login") { inclusive = true }
            }}, navController = navController)
        }
        composable("register") {
            RegisterScreen (onRegistrationSuccess = {user -> navController.navigate("home") {
                popUpTo("register") { inclusive = true }
            }}, navController = navController)
        }
        composable("forgotPassword") {
            ForgotPasswordScreen (navController = navController)
        }
    }
}