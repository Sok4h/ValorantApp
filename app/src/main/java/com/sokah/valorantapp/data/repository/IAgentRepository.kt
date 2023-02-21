package com.sokah.valorantapp.data.repository

import com.sokah.valorantapp.data.model.entities.AgentEntity
import com.sokah.valorantapp.ui.mapper.uiModel.AgentModel

interface IAgentRepository {

    suspend fun getAllAgents(): Result<MutableList<AgentModel>>
    suspend fun addAgents(agents: MutableList<AgentEntity>)
    suspend fun getAllAgentsFromDatabase(): MutableList<AgentModel>
    suspend fun getAgentByRole(role: String): MutableList<AgentModel>
    suspend fun getAgentById(id: String): AgentModel
}