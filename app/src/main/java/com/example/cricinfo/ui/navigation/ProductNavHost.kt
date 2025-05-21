package com.example.cricinfo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cricinfo.ui.homepage.products.ProductDetailScreen
import com.example.cricinfo.ui.homepage.products.ProductListScreen
import com.example.cricinfo.ui.homepage.products.ProductScreen
import com.example.cricinfo.ui.model.Product

@Composable
fun ProductNavHost() {
    val navController = rememberNavController()

    val sampleProducts = listOf(
        Product(1, "Laptop", "High performance laptop"),
        Product(2, "Phone", "Latest Android smartphone"),
        Product(3, "Headphones", "Noise-cancelling headphones")
    )


    NavHost(navController = navController, startDestination = ProductScreen.ProductList.route) {
        composable(ProductScreen.ProductList.route) {
            ProductListScreen(products = sampleProducts, onItemClick = { product ->
                navController.navigate(ProductScreen.ProductDetail.createRoute(product.id))
            })
        }
        composable(
            route = ProductScreen.ProductDetail.route,
            arguments = listOf(navArgument("productId") { type = NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            val product = sampleProducts.find { it.id == productId }
            product?.let {
                ProductDetailScreen(it)
            }
        }
    }
}
