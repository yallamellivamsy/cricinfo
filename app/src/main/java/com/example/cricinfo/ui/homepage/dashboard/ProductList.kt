package com.example.cricinfo.ui.homepage.dashboard

import androidx.compose.runtime.Composable
import com.example.cricinfo.ui.model.Product1

@Composable
fun ProductList(products: List<Product1>) {
    products.forEach { product ->
        ProductCard(product)
    }
}