package com.example.cricinfo.data.remote

import android.util.Log
import com.example.cricinfo.domain.model.User
import com.example.cricinfo.domain.model.UserProfile
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class FirebaseAuthService @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
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

    suspend fun register(
        email: String,
        password: String,
        name: String,
        phone: String,
        dob: String
    ): Result<User> = try {
        // Step 1: Create user
        val result = auth.createUserWithEmailAndPassword(email, password).await()
        val uid = result.user?.uid ?: throw Exception("Registration failed: No UID")

        // Step 2: Build user profile map
        val userProfile = mapOf(
            "uid" to uid,
            "name" to name,
            "phone" to phone,
            "dob" to dob,
            "email" to email
        )

        // Step 3: Save to Firestore under "users" collection
        firestore.collection("users").document(uid).set(userProfile).await()

        Result.success(User(uid)) // Assuming User model has uid only
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun getUserData(): UserProfile? {
        val uid = auth.currentUser?.uid.toString()
        return try {
            val snapshot = firestore
                .collection("users")
                .document(uid)
                .get()
                .await()

            snapshot.toObject(UserProfile::class.java)  // your custom data model
        } catch (e: Exception) {
            Log.e("Home", "Failed to load user data: ${e.message}")
            null
        }
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