package com.sokah.valorantapp.ui.mapper.uiModel

import com.sokah.valorantapp.model.weapons.Chroma
import com.sokah.valorantapp.model.weapons.Level

data class SkinModel(
    val chromas: List<Chroma>,
    val displayIcon: String?,
    val displayName: String,
    val levels: List<Level>,
    val themeUuid: String,
    val uuid: String,
)