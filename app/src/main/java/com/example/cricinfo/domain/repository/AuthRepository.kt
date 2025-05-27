package com.example.cricinfo.domain.repository

import android.content.Context
import com.example.cricinfo.domain.model.User
import com.example.cricinfo.domain.model.UserProfile
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun register(email: String, password: String, name: String, mobileNumber: String, dob: String): Result<User>
    suspend fun sendPasswordResetEmail(email: String): Result<String>
    suspend fun getRememberedEmail(): Flow<String?>
    suspend fun saveEmailForRememberMe(email: String)
    suspend fun clearEmail()
    suspend fun getUserData(): UserProfile?
    fun isUserLoggedIn(): Boolean
}