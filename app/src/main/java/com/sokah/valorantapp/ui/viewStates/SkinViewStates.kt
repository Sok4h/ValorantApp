package com.sokah.valorantapp.ui.viewStates

import com.sokah.valorantapp.ui.mapper.uiModel.SkinModel

sealed interface SkinViewStates {

    data class Success(val data: MutableList<SkinModel>) : SkinViewStates
    data class Error(val error: Exception) : SkinViewStates
    object Loading : SkinViewStates
}