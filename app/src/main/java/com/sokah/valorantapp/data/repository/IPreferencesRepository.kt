package com.sokah.valorantapp.data.repository

import kotlinx.coroutines.flow.Flow

interface IPreferencesRepository {

    suspend fun savePreference(value: Int)

    suspend fun getPreference(): Flow<Int>


}