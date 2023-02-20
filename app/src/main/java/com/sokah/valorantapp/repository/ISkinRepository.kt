package com.sokah.valorantapp.repository

import com.sokah.valorantapp.model.weapons.Skin

interface ISkinRepository {

    suspend fun getAllSkins(): MutableList<Skin>
    suspend fun getAllSkinsdb(): MutableList<Skin>
    suspend fun addSkins(data: MutableList<Skin>)
    suspend fun getSkinByType(type: String): MutableList<Skin>
    suspend fun getSkinByUuid(uuid: String): Skin
}