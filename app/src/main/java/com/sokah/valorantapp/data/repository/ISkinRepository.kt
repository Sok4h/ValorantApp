package com.sokah.valorantapp.data.repository

import com.sokah.valorantapp.ui.mapper.uiModel.SkinModel
import com.sokah.valorantapp.data.model.entities.SkinEntity

interface ISkinRepository {

    suspend fun getAllSkins(): MutableList<SkinModel>
    suspend fun getAllSkinsdb(): MutableList<SkinModel>
    suspend fun addSkins(data: MutableList<SkinEntity>)
    suspend fun getSkinByType(type: String): MutableList<SkinModel>
    suspend fun getSkinByUuid(uuid: String): SkinModel
}