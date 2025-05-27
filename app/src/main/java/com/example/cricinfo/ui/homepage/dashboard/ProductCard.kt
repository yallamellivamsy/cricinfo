package com.example.cricinfo.ui.homepage.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.cricinfo.ui.model.Product1
import androidx.compose.material3.Icon
import com.example.cricinfo.R

@Composable
fun ProductCard(product: Product1) {
    var isFavorite by remember { mutableStateOf(false) }
    var itemCount by remember { mutableStateOf(0) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            Box(modifier = Modifier.fillMaxWidth()) {
                AsyncImage(
                    model = R.drawable.carousel_2,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(200.dp)
                        .height(75.dp)
                        .clip(MaterialTheme.shapes.medium)
                )

                IconButton(
                    onClick = { isFavorite = !isFavorite },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color.Red else Color.Gray
                    )
                }
            }

            Spacer(Modifier.height(8.dp))

            Text(text = product.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "₹${product.price}", style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.height(8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (itemCount == 0) {
                    Button(onClick = { itemCount++ }) {
                        Text("Add to Cart")
                    }
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { if (itemCount > 0) itemCount-- }) {
                            Icon(Icons.Default.Remove, contentDescription = "Remove")
                        }
                        Text("$itemCount", style = MaterialTheme.typography.bodyMedium)
                        IconButton(onClick = { itemCount++ }) {
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }
                    }
                }
            }
        }
    }
}
