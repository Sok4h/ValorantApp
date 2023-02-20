package com.sokah.valorantapp.repository

import com.sokah.valorantapp.model.dataModel.AgentModel
import com.sokah.valorantapp.model.entities.AgentEntity

interface IAgentRepository {

    suspend fun getAllAgents(): MutableList<AgentModel>?
    suspend fun addAgents(agents: MutableList<AgentEntity>)
    suspend fun getAllAgentsdb(): MutableList<AgentModel>?
    suspend fun getAgentByRole(role: String): MutableList<AgentModel>?
    suspend fun getAgentById(id: String): AgentModel
}