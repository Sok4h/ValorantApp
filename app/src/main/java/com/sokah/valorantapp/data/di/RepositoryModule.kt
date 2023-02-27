package com.sokah.valorantapp.data.di

import com.sokah.valorantapp.data.repository.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

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