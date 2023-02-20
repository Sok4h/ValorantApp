package com.sokah.valorantapp.repository

import com.sokah.valorantapp.model.dataModel.SkinModel
import com.sokah.valorantapp.model.entities.SkinEntity

interface ISkinRepository {

    suspend fun getAllSkins(): MutableList<SkinModel>
    suspend fun getAllSkinsdb(): MutableList<SkinModel>
    suspend fun addSkins(data: MutableList<SkinEntity>)
    suspend fun getSkinByType(type: String): MutableList<SkinModel>
    suspend fun getSkinByUuid(uuid: String): SkinModel
}