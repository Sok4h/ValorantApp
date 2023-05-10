package com.sokah.valorantapp.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.sokah.valorantapp.data.database.AgentDao
import com.sokah.valorantapp.data.database.SkinDao
import com.sokah.valorantapp.data.database.ValorantDatabase
import com.sokah.valorantapp.data.database.WeaponDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    private const val PREFERENCES_NAME = "my_preferences"

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): ValorantDatabase {

        return Room.databaseBuilder(context, ValorantDatabase::class.java, "mydb")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    fun provideAgentDao(database: ValorantDatabase): AgentDao {

        return database.agentDao()
    }


    @Provides
    fun provideWeaponDao(database: ValorantDatabase): WeaponDao {

        return database.weaponDao()
    }

    @Provides
    fun provideSkinDao(database: ValorantDatabase): SkinDao {

        return database.skinDao()
    }

    @Singleton
    @Provides
    fun providePreferencesDataStore(@ApplicationContext appContext: Context): DataStore<Preferences> {

        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            produceFile = { appContext.preferencesDataStoreFile(PREFERENCES_NAME) }
        )

    }


}

