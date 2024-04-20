package com.example.androidprojecttemplate.core.data_store.implementation

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SharedPrefsService(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "android_template_key_value_data")

    fun <T> getPreference(key: Preferences.Key<T>, defaultValue: T):
            Flow<T?> {
        return context.dataStore.data.map {
            showLog("getPreference(${key})")
            it[key]
        }
    }

    suspend fun <T> putPreference(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { preferences ->
            showLog("putPreference(${key})")
            preferences[key] = value
        }
    }

    suspend fun <T> removePreference(key: Preferences.Key<T>) {
        context.dataStore.edit {
            showLog("removePreference(${key})")
            it.remove(key)
        }
    }

    suspend fun clearAllPreference() {
        context.dataStore.edit { preferences ->
            showLog("clearAllPreference()")
            preferences.clear()
        }
    }

    private fun showLog(message: String) {
        Log.w("SharedPrefsService", "Invoke $message")
    }
}