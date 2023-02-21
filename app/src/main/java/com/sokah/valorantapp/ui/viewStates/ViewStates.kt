package com.sokah.valorantapp.ui.viewStates

import com.sokah.valorantapp.data.model.BaseModel
import com.sokah.valorantapp.ui.mapper.uiModel.AgentModel

sealed interface AgentViewStates {

    data class AllAgents(val data: List<AgentModel>) : AgentViewStates
    object Loading : AgentViewStates
    object Empty : AgentViewStates
    object Error:AgentViewStates
}