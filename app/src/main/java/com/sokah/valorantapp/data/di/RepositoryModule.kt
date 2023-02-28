package com.sokah.valorantapp.data.di

import com.sokah.valorantapp.data.repository.*
import dagger.Binds
import dagger.Module
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