package com.example.cricinfo.ui.homepage.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.cricinfo.R
import com.example.cricinfo.ui.components.Carousel
import com.example.cricinfo.ui.model.Product1

@Composable
fun HomePage() {
    val horizontalItems = listOf("Card 1", "Card 2", "Card 3", "Card 4", "Card 5")
    val verticalItems = List(10) { "List Item ${it + 1}" }


    val sampleProducts = listOf(
        Product1(1, "HealthCare", 999.0, "https://via.placeholder.com/400x300"),
        Product1(2, "Beauty", 499.0, "https://via.placeholder.com/400x300"),
        Product1(3, "HealthCare", 1499.0, "https://via.placeholder.com/400x300")
    )

    // Box with background image
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
//        Image(
//            painter = painterResource(), // Add your image in drawable folder
//            contentDescription = "Background Image",
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.Crop
//        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(16.dp)
        ) {
            item {
                Text(
                    text = "Boots Products",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Divider()
                Carousel(modifier = Modifier.padding(4.dp))

                Spacer(modifier = Modifier.height(24.dp))

                Divider(modifier = Modifier.height(1.dp))
                Text(
                    text = "Most Purchased Items",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 3.dp)
                )
                Divider()
                ProductList(products = sampleProducts)

            }
        }
    }
}

@Composable
fun SimpleCardWithImageAndText() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp), // Add padding around the card
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painter = painterResource(id = R.mipmap.popular_product_1), // Replace with your image
                contentDescription = "Product Image",
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterVertically)
            )

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Product Title",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$50.00",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}
