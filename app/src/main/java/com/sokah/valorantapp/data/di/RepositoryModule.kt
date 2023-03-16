package com.sokah.valorantapp.data.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.sokah.valorantapp.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)

abstract class RepositoryModule {

    @Binds
    abstract fun bindAgentRepository(
        agentRepository: AgentRepository
    ): IAgentRepository

    @Binds
    abstract fun bindWeaponRepository(
        weaponRepository: WeaponRepository
    ): IWeaponRepository

    @Binds
    abstract fun bindSkinRepository(
        skinRepository: SkinRepository
    ): ISkinRepository
}

@Module
@InstallIn(ViewModelComponent::class)
object PreferenceModule {

    @Provides
    fun provideMainRepositoryImpl(dataStore: DataStore<Preferences>): IPreferencesRepository {

        return PreferencesRepository(dataStore)
    }
}

