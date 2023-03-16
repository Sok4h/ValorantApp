package com.sokah.valorantapp.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val AGENT_FILTER_PREFERENCE = intPreferencesKey("agentChip")

class PreferencesRepository @Inject constructor(
    val datastore: DataStore<Preferences>
) : IPreferencesRepository {

    override suspend fun savePreference(value: Int) {


        datastore.edit { preferences ->
            preferences[AGENT_FILTER_PREFERENCE] = value
        }
    }

    override suspend fun getPreference(): Flow<Int> {

        return datastore.data.catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->

            val value = preferences[AGENT_FILTER_PREFERENCE] ?: 0
            value

        }
    }


}
