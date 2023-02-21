package com.sokah.valorantapp.data.repository

import android.util.Log
import com.sokah.valorantapp.MyApplication
import com.sokah.valorantapp.data.database.ValorantDatabase
import com.sokah.valorantapp.data.model.BaseModel
import com.sokah.valorantapp.model.agents.AgentDto
import com.sokah.valorantapp.ui.mapper.uiModel.AgentModel
import com.sokah.valorantapp.data.model.entities.AgentEntity
import com.sokah.valorantapp.data.model.toAgentEntity
import com.sokah.valorantapp.data.network.ValorantApiService
import com.sokah.valorantapp.ui.mapper.uiMappers.toAgentModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.sokah.valorantapp.data.utils.Result
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


class AgentRepository() : IAgentRepository {
    private val database: ValorantDatabase by lazy { MyApplication.getDatabase() }
    private val agentDao = database.agentDao()
    private var service = ValorantApiService()


    override suspend fun getAllAgents(): Result<MutableList<AgentModel>> {


        var result: MutableList<AgentModel> = mutableListOf()

        val response: Response<BaseModel<MutableList<AgentDto>>>

        return withContext(Dispatchers.IO) {

            try {
                val response = service.getAgents()
                when {

                    response.isSuccessful -> {

                        val resultApi = response.body()

                        addAgents(resultApi!!.data.map { it.toAgentEntity() }.toMutableList())

                        Result.Success(getAllAgentsdb())
                    }
                    else -> {

                        /* result = getAllAgentsdb()
                         if (result.isEmpty()) {*/
                        Log.e("error", "cacheeee")
                        Result.Failure(NoCacheException("No hay caché pailas"))

                        /* }
                         Result.success(getAllAgentsdb())*/
                    }
                }
            } catch (e: Exception) {
                result = getAllAgentsdb()

                if (result.isEmpty()) {

                    return@withContext Result.Failure<NoCacheException>(NoCacheException("No hay caché pailas"))
                }
            }

        }
    }


    override suspend fun addAgents(agents: MutableList<AgentEntity>) {

        agentDao.insertAgents(agents)
    }

    override suspend fun getAllAgentsdb(): MutableList<AgentModel> {

        return agentDao.getAllAgents().map { it.toAgentModel() }.toMutableList()
    }

    override suspend fun getAgentByRole(role: String): MutableList<AgentModel> {

        return agentDao.getAgentbyRole(role).map { it.toAgentModel() }.toMutableList()

    }


    override suspend fun getAgentById(id: String): AgentModel {

        return agentDao.getAgentById(id).toAgentModel()
    }
}