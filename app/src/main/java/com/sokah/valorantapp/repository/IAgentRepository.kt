package com.sokah.valorantapp.repository

import com.sokah.valorantapp.model.agents.AgentModel

interface IAgentRepository {

    suspend fun getAllAgents(): MutableList<AgentModel>?
    suspend fun addAgents(agents: MutableList<AgentModel>)
    suspend fun getAllAgentsdb(): MutableList<AgentModel>?
    suspend fun getAgentByRole(role: String): MutableList<AgentModel>?
    suspend fun getAgentById(id: String): AgentModel
}