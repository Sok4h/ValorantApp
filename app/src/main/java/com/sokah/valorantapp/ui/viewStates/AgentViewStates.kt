package com.sokah.valorantapp.ui.viewStates

import com.sokah.valorantapp.ui.mapper.uiModel.AgentModel

sealed interface AgentViewStates {

    data class AgentListSuccess(val data: MutableList<AgentModel>) : AgentViewStates
    data class AgentSuccess(val data: AgentModel) : AgentViewStates
    object Loading : AgentViewStates
    class Error(val error: Exception) : AgentViewStates
}