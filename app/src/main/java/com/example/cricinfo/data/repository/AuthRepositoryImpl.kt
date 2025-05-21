package com.example.cricinfo.data.repository

import android.content.Context
import com.example.cricinfo.data.local.UserPreferences
import com.example.cricinfo.data.remote.FirebaseAuthService
import com.example.cricinfo.domain.model.User
import com.example.cricinfo.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

// data/repository/AuthRepositoryImpl.kt
class AuthRepositoryImpl @Inject constructor(
    private val authService: FirebaseAuthService,
    private val userPreferences: UserPreferences
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<User> {
        return authService.login(email, password)
    }

    override suspend fun register(email: String, password: String): Result<User> {
        return authService.register(email, password)
    }

    // Forgot Password Functionality
    override suspend fun sendPasswordResetEmail(email: String): Result<String> {
        return authService.sendPasswordResetEmail(email)
    }

    override fun getRememberedEmail(): Flow<String?> {
        return userPreferences.getSavedEmail()
    }
    override suspend fun saveEmailForRememberMe(email: String) {
        userPreferences.saveEmail(email)
    }

    override suspend fun clearEmail() {
        userPreferences.clearEmail()
    }

    override fun isUserLoggedIn(): Boolean {
        return authService.isUserLoggedIn()
    }
}
