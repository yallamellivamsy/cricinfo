package com.example.cricinfo.ui.homepage.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CartScreen() {
    // Define the cart items as a list of CartItem objects
    val cartItems = listOf(
        CartItem("Product 1", "$20.00"),
        CartItem("Product 2", "$35.00"),
        CartItem("Product 3", "$12.00")
    )

    // Total price
    val total = "$67.00"

    // Layout of the CartPage
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F4F8)) // Light background color
            .padding(16.dp)
    ) {
        // Header Section
        Text(
            text = "My Cart",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        LazyColumn {
            items(cartItems) { item ->
                CartItemView(item)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        Text(
            text = "Total: $total",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Proceed Button
        Button(
            onClick = { /* Handle checkout logic here */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6200EE)) // Purple color for button
        ) {
            Text(text = "Proceed to Checkout", color = Color.White)
        }
    }
}

@Composable
fun CartItemView(cartItem: CartItem) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = cartItem.name, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = cartItem.price, style = MaterialTheme.typography.bodySmall)
            }
            IconButton(onClick = { /* Handle remove item logic here */ }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Remove Item",
                    tint = Color.Red
                )
            }
        }
    }
}

// CartItem data class
data class CartItem(val name: String, val price: String)
