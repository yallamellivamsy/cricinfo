package com.example.cricinfo.ui.homepage.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.cricinfo.domain.model.UserProfile
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen1(navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    //Text("Profile Screen")
    Text(
        text = "Log out",
        modifier = Modifier.clickable {
            // Sign out the user
            auth.signOut()
            navController.navigate("login")
        }
    )
}

@Composable
fun ProfileScreen(
    navController: NavController,
    userData: UserProfile?,
    userName: String = "vamsy",
    userEmail: String = "vamsy@gmail.com",
    onLogoutClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},

    ) {
    val auth = FirebaseAuth.getInstance()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        // Profile picture placeholder
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = userData?.name?.firstOrNull()?.toString() ?: "U",
                fontSize = 40.sp,
                fontWeight = Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = userData?.name.toString(),
            style = MaterialTheme.typography.headlineSmall
        )

        Text(
            text = userEmail,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onEditClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Edit Profile")
        }

        Button(
            onClick = onSettingsClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        ) {
            Text("Settings")
        }

        OutlinedButton(
            onClick = onLogoutClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.error
            )
        ) {
            Text(
                text = "Log out",
                modifier = Modifier.clickable {
                    // Sign out the user
                    auth.signOut()
                    navController.navigate("login")
                }
            )
        }
    }
}
