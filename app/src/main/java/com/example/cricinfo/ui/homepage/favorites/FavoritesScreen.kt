package com.example.cricinfo.ui.homepage.favorites

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cricinfo.ui.model.Product1
import com.example.cricinfo.R

@Composable
fun FavoritesScreen(
    favoriteProducts: List<Product1>,
    onToggleFavorite: (Product1) -> Unit  // callback to toggle favorite
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (favoriteProducts.isEmpty()) {
            item {
                Text(
                    "No favorites yet!",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    color = Color.Gray,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        } else {
            items(favoriteProducts) { product ->
                FavoriteProductCard(
                    product = product,
                    onToggleFavorite = { onToggleFavorite(product) }
                )
            }
        }
    }
}

@Composable
fun FavoriteProductCard(
    product: Product1,
    onToggleFavorite: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = R.drawable.carousel_1,
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(96.dp)
                    .weight(0.3f)
                    .clip(RoundedCornerShape(8.dp))
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column(
                modifier = Modifier.weight(0.5f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(product.name, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Text("$${product.price}", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }

            IconButton(
                onClick = onToggleFavorite,
                modifier = Modifier.weight(0.2f)
            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Toggle Favorite",
                    tint = Color.Red
                )
            }
        }
    }
}
