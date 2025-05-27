package com.example.cricinfo.ui.register

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.cricinfo.domain.model.User
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.cricinfo.R

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    onRegistrationSuccess: (User) -> Unit,
    navController: NavHostController
) {

    val state by viewModel.state.collectAsState()
    var name by remember { mutableStateOf("") }
    var mobileNumber by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
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
                    Text("Register", style = MaterialTheme.typography.headlineLarge)
                    OutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = { Text("Name") }
                    )
                    OutlinedTextField(
                        value = mobileNumber,
                        onValueChange = { mobileNumber = it },
                        label = { Text("Mobile Number") }
                    )
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Username") }
                    )
                    DatePickerScreen(dateOfBirth = dob,
                        onDateSelected = { selectedDate ->
                            dob = selectedDate
                        },
                        modifier = Modifier.padding(start = 32.dp, top = 10.dp, end = 32.dp, bottom = 0.dp))
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
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { Text("confirmPassword") },
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
                        modifier = Modifier.padding(0.dp)
                    )
                    Button(onClick = { viewModel.register(email, password, confirmPassword, name, mobileNumber, dob) }) {
                        Text("Register")
                    }
                    when (state) {
                        is RegisterState.Loading -> CircularProgressIndicator()
                        is RegisterState.Error -> Text("Error: ${(state as RegisterState.Error).message}")
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
                        // Navigate to the login screen
                        navController.navigate("login"){
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.padding(0.dp)
                ) {
                    Text("Already have an account? Login Here")
                }

            }
        }
    }

    // Trigger side effect (navigation) in a safe way
    if (state is RegisterState.Success) {
        LaunchedEffect(Unit) {
            onRegistrationSuccess((state as RegisterState.Success).user)
        }
    }
}
