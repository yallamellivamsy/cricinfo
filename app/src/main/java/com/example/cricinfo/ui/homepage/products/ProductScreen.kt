package com.example.cricinfo.ui.homepage.products

sealed class ProductScreen(val route: String) {
    object ProductList : ProductScreen("product_list")
    object ProductDetail : ProductScreen("product_detail/{productId}") {
        fun createRoute(productId: Int) = "product_detail/$productId"
    }
}