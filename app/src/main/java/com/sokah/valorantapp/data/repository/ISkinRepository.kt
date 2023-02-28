package com.sokah.valorantapp.data.repository

import com.sokah.valorantapp.data.model.entities.SkinEntity
import com.sokah.valorantapp.ui.mapper.uiModel.SkinModel

interface ISkinRepository {

    suspend fun getAllSkins(): Result<MutableList<SkinModel>>
    suspend fun getAllSkinsFromDatabase(): MutableList<SkinModel>
    suspend fun addSkins(data: MutableList<SkinEntity>)
    suspend fun getSkinByType(type: String): MutableList<SkinModel>
    suspend fun getSkinByUuid(uuid: String): SkinModel
}