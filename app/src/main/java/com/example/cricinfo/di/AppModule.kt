package com.example.cricinfo.di

import android.content.Context
import com.example.cricinfo.data.local.UserPreferences
import com.example.cricinfo.data.remote.FirebaseAuthService
import com.example.cricinfo.data.repository.AuthRepositoryImpl
import com.example.cricinfo.domain.repository.AuthRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.components.SingletonComponent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseAuthService(auth: FirebaseAuth, store: FirebaseFirestore ): FirebaseAuthService {
        return FirebaseAuthService(auth, store)
    }

    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences {
        return UserPreferences(context)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        firebaseAuthService: FirebaseAuthService,
        userPreferences: UserPreferences
    ): AuthRepository {
        return AuthRepositoryImpl(firebaseAuthService, userPreferences)
    }



}
