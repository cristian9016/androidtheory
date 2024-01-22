package com.example.androidtheory.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

object Prefs {
    private var _prefs: AndroidTheoryPreferences? = null
    fun getPrefs() = _prefs
    fun initPrefs(context: Context) {
        _prefs = AndroidTheoryPreferences(context)
    }
}

class AndroidTheoryPreferences(context: Context) {
    private val DATA_IS_LOADED = booleanPreferencesKey("data_is_loaded")

    private val dataSource = context.dataStore

    suspend fun saveDataLoaded(isDataLoaded: Boolean) {
        dataSource.edit { preferences ->
            preferences[DATA_IS_LOADED] = isDataLoaded
        }
    }

    suspend fun isDataLoaded(): Boolean {
        return dataSource.data.first()[DATA_IS_LOADED] ?: false
    }
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "androidtheory")
