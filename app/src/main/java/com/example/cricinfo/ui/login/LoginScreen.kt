package com.example.cricinfo.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.cricinfo.R
import com.example.cricinfo.domain.model.User
import com.example.cricinfo.ui.components.CheckboxWithText


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: (User) -> Unit,
    navController: NavHostController
) {

    val state by viewModel.state.collectAsState()
    val email = viewModel.email
    var rememberMe by rememberSaveable { mutableStateOf(true) }
    var password by remember { mutableStateOf("") }
    val isPasswordVisible = remember { mutableStateOf(false) }

    // Toggle the visibility of the password
    val visualTransformation: VisualTransformation = if (isPasswordVisible.value) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    // Define the icon based on password visibility
    val visibilityIcon: ImageVector = if (isPasswordVisible.value) {
        Icons.Default.Visibility
    } else {
        Icons.Default.VisibilityOff
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFBBDEFB))
    ) {

        // Background image
        Image(
            painter = painterResource(R.mipmap.auth_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Login", style = MaterialTheme.typography.headlineLarge)
                    OutlinedTextField(
                        value = email,
                        onValueChange = { viewModel.onEmailChanged(it) },
                        label = { Text("Email") }
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        visualTransformation = visualTransformation,
                        trailingIcon = {
                            IconButton(onClick = {
                                isPasswordVisible.value = !isPasswordVisible.value
                            }) {
                                Icon(
                                    imageVector = visibilityIcon,
                                    contentDescription = "Toggle password visibility"
                                )
                            }
                        },
                        modifier = Modifier.padding(16.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Using the CheckboxWithText composable
                        CheckboxWithText(
                            text = "Remember me",
                            isChecked = rememberMe,
                            onCheckedChange = { rememberMe = it }
                        )

                        Text(
                            text = "Forgot Password?",
                            modifier = Modifier
                                .clickable {
                                    // Action when text is clicked
                                    navController.navigate("forgotPassword")
                                }
                                .padding(16.dp),  // Optional padding for better touch area
                        )
                    }

                    Button(onClick = { viewModel.login(email, password) }) {
                        Text("Login")
                    }
                    when (state) {
                        is LoginState.Loading -> CircularProgressIndicator()
                        is LoginState.Error -> Text("Error: ${(state as LoginState.Error).message}")
                        else -> {}
                    }
                }
            }
            Card(
                modifier = Modifier
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFE3F2FD)),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                TextButton(
                    onClick = {
                        // Navigate to the register screen
                        navController.navigate("register")
                    },
                    modifier = Modifier.padding(0.dp)
                ) {
                    Text("Don't have an account? Register Here")
                }

            }
        }
    }

    LaunchedEffect(state) {
        if (state is LoginState.Success) {
            viewModel.saveRememberedEmail(rememberMe)
            onLoginSuccess((state as LoginState.Success).user)
        }
    }
}