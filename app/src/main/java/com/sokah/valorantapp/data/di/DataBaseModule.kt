package com.sokah.valorantapp.data.di

import android.content.Context
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
}

