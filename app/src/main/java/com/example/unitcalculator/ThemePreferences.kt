package com.example.unitcalculator

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "settings")

class ThemePreferences(context: Context) {
    private val ds = context.dataStore
    private val DARK_MODE_KEY = booleanPreferencesKey("dark_mode")

    val darkModeFlow: Flow<Boolean?> = ds.data.map { prefs -> prefs[DARK_MODE_KEY] }

    suspend fun setDarkMode(value: Boolean) {
        ds.edit { prefs -> prefs[DARK_MODE_KEY] = value }
    }
}