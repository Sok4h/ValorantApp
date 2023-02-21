package com.sokah.valorantapp.ui.viewStates

import com.sokah.valorantapp.ui.mapper.uiModel.AgentModel

sealed interface AgentViewStates {

    data class Success(val data: List<AgentModel>) : AgentViewStates
    object Loading : AgentViewStates
    class Error(val error: Exception) : AgentViewStates
}