package com.example.cricinfo.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// UserPreferences.kt (in data layer)
@Singleton
class UserPreferences @Inject constructor(
    private val context: Context
) {
    private val Context.dataStore by preferencesDataStore(name = "user_prefs")

    private val REMEMBERED_EMAIL = stringPreferencesKey("remembered_email")

    suspend fun saveEmail(email: String) {
        context.dataStore.edit { prefs ->
            prefs[REMEMBERED_EMAIL] = email
        }
    }

    suspend fun getSavedEmail(): Flow<String?> {
        return context.dataStore.data.map { prefs ->
            prefs[REMEMBERED_EMAIL]
        }
    }

    suspend fun clearEmail() {
        context.dataStore.edit { prefs ->
            prefs.remove(REMEMBERED_EMAIL)
        }
    }

}