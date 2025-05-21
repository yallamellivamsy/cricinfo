package com.example.cricinfo.ui.homepage.products

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ListItem
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.cricinfo.ui.model.Product

@Composable
fun ProductListScreen(products: List<Product>, onItemClick: (Product) -> Unit) {
    Scaffold(
//        topBar = {
//            TopAppBar(title = { Text("Products") })
//        }
    ) { padding ->
        LazyColumn(contentPadding = padding) {
            items(products, key = { it.id }) { product ->
                ListItem(
                    headlineContent = {
                        Text(product.description)
                    },
                    supportingContent = {
                        Text(product.name)
                    },
                    modifier = Modifier
                        .clickable { onItemClick(product) }
                        .padding(0.dp)
                )
                Divider()
            }
        }
//        val items: List<String> = List(20) { "Item #$it" }
//
//        LazyColumn {
//            items(items) { item ->
//                Text(text = item)
//            }
//        }
    }
}
