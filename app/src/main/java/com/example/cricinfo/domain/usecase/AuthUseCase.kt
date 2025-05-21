package com.example.cricinfo.domain.usecase

import com.example.cricinfo.domain.model.User
import com.example.cricinfo.domain.repository.AuthRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class AuthUseCase @Inject constructor(
    private val repository: AuthRepository
) {

    suspend fun loginUseCase(email: String, password: String): Result<User> {
        return repository.login(email, password)
    }

    suspend fun registerUseCase(email: String, password: String): Result<User> {
        return repository.register(email, password)
    }

    fun isUserLoggedInUseCase(): Boolean{
        return repository.isUserLoggedIn()
    }

    fun getEmailUseCase(): Flow<String?> {
        return repository.getRememberedEmail()
    }

    suspend fun saveEmailUseCase(email: String) {
        repository.saveEmailForRememberMe(email)
    }

    suspend fun clearEmailUseCase() {
        repository.clearEmail()
    }

    suspend fun forgotPasswordUseCase(email: String): Result<String> {
        return repository.sendPasswordResetEmail(email)
    }

}