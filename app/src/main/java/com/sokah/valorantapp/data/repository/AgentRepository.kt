package com.sokah.valorantapp.data.repository

import com.sokah.valorantapp.data.database.AgentDao
import com.sokah.valorantapp.data.exceptions.CustomException
import com.sokah.valorantapp.data.exceptions.ErrorMessages
import com.sokah.valorantapp.data.model.entities.AgentEntity
import com.sokah.valorantapp.data.model.toAgentEntity
import com.sokah.valorantapp.data.network.ValorantApiService
import com.sokah.valorantapp.ui.mapper.uiMappers.toAgentModel
import com.sokah.valorantapp.ui.mapper.uiModel.AgentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class AgentRepository @Inject constructor(
    private val service: ValorantApiService,
    private val agentDao: AgentDao
) : IAgentRepository {

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
                                ErrorMessages.API_FAILED_AND_NO_CACHE.error
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
                        Result.failure(CustomException(ErrorMessages.NO_INTERNET_CONNECTION.error))
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


    override suspend fun getAgentById(id: String): Result<AgentModel> {


        val response = agentDao.getAgentById(id)

        return if (response != null) {

            Result.success(response.toAgentModel())
        } else {

            Result.failure(CustomException(ErrorMessages.NO_AGENT_FOUND_WITH_UUID.error))
        }


    }
}