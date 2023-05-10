package com.sokah.valorantapp.data.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sokah.valorantapp.ui.mapper.uiModel.SkinModel
import com.sokah.valorantapp.model.weapons.Chroma
import com.sokah.valorantapp.model.weapons.Level

@Entity(tableName = "skins")
data class SkinEntity(
    val chromas: List<Chroma>,
    val displayIcon: String?,
    val displayName: String,
    val levels: List<Level>,
    val themeUuid: String,
    @PrimaryKey
    val uuid: String,
)

