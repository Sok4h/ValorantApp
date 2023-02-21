package com.sokah.valorantapp.data.repository

import com.sokah.valorantapp.MyApplication
import com.sokah.valorantapp.data.database.ValorantDatabase
import com.sokah.valorantapp.data.exceptions.CustomException
import com.sokah.valorantapp.data.model.entities.AgentEntity
import com.sokah.valorantapp.data.model.toAgentEntity
import com.sokah.valorantapp.data.network.ValorantApiService
import com.sokah.valorantapp.ui.mapper.uiMappers.toAgentModel
import com.sokah.valorantapp.ui.mapper.uiModel.AgentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class AgentRepository() : IAgentRepository {
    private val database: ValorantDatabase by lazy { MyApplication.getDatabase() }
    private val agentDao = database.agentDao()
    private var service = ValorantApiService()


    override suspend fun getAllAgents(): Result<MutableList<AgentModel>> {
        var databaseResult: MutableList<AgentModel>
        var result: Result<MutableList<AgentModel>>

        return withContext(Dispatchers.IO) {

            databaseResult = getAllAgentsFromDatabase()
            try {
                val response = service.getAgents()
                if (response.isSuccessful) {
                    val resultApi = response.body()
                    addAgents(resultApi!!.data.map { it.toAgentEntity() }.toMutableList())
                    result = Result.success(getAllAgentsFromDatabase())
                } else if (databaseResult.isEmpty()) {
                    result =
                        Result.failure(
                            CustomException(
                                "Si hay internet pero la api fallo con codigo"
                                        + " ${response.code()} y la base de datos está vacía"
                            )
                        )
                } else {
                    result = Result.success(getAllAgentsFromDatabase())
                }
            } catch (e: Exception) {
                if (databaseResult.isNotEmpty()) {
                    return@withContext Result.success(databaseResult)
                } else {
                    result =
                        Result.failure(CustomException("No tenemos internet y la base de datos está vacia"))
                }
            }
            return@withContext result
        }
    }


    override suspend fun addAgents(agents: MutableList<AgentEntity>) {

        agentDao.insertAgents(agents)
    }

    override suspend fun getAllAgentsFromDatabase(): MutableList<AgentModel> {

        return agentDao.getAllAgents().map { it.toAgentModel() }.toMutableList()
    }

    override suspend fun getAgentByRole(role: String): MutableList<AgentModel> {

        return agentDao.getAgentbyRole(role).map { it.toAgentModel() }.toMutableList()

    }


    override suspend fun getAgentById(id: String): AgentModel {

        return agentDao.getAgentById(id).toAgentModel()
    }
}