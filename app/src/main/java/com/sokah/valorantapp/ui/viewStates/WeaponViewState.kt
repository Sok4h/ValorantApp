package com.sokah.valorantapp.ui.viewStates

import com.sokah.valorantapp.ui.mapper.uiModel.WeaponModel

sealed interface WeaponViewState {

    data class Success(val data: MutableList<WeaponModel>) : WeaponViewState
    data class Error(val error: Exception) : WeaponViewState
    object Loading : WeaponViewState

}