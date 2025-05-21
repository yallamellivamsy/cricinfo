package com.example.cricinfo.data.remote

import com.example.cricinfo.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirebaseAuthService @Inject constructor(
    private val auth: FirebaseAuth
) {
    suspend fun login(email: String, password: String): Result<User> = try {
        val result = auth.signInWithEmailAndPassword(email, password).await()
        Result.success(User(result.user?.uid ?: ""))
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun register(email: String, password: String): Result<User> = try {
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        Result.success(User(result.user?.uid ?: ""))
    } catch (e: Exception) {
        Result.failure(e)
    }



    // Forgot Password Functionality
    suspend fun sendPasswordResetEmail(email: String): Result<String> =
        withContext(Dispatchers.IO) {
            if (email.isBlank()) {
                return@withContext Result.failure(Exception("Email cannot be empty"))
            }
            try {
                auth.sendPasswordResetEmail(email).await()
                Result.success("Reset link sent! Check your email.")
            } catch (e: Exception) {
                Result.failure(e)
            }
        }

    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }
}