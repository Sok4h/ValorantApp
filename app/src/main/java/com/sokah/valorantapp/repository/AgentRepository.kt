package com.sokah.valorantapp.repository

import android.util.Log
import com.sokah.valorantapp.MyApplication
import com.sokah.valorantapp.db.ValorantDatabase
import com.sokah.valorantapp.model.BaseModel
import com.sokah.valorantapp.model.agents.AgentDto
import com.sokah.valorantapp.model.agents.toAgentEntity
import com.sokah.valorantapp.model.dataModel.AgentModel
import com.sokah.valorantapp.model.entities.AgentEntity
import com.sokah.valorantapp.model.entities.toAgentModel
import com.sokah.valorantapp.network.ValorantApiService
import retrofit2.HttpException
import java.io.IOException

class AgentRepository():IAgentRepository {
    private val database :ValorantDatabase by lazy { MyApplication.getDatabase() }
     private val agentDao = database.agentDao()
    private var service = ValorantApiService()
    private val conflict ="this should cause conflict since its a new PR"


    override suspend fun getAllAgents(): MutableList<AgentModel>? {

        var resultApi: BaseModel<MutableList<AgentDto>>? = null
        var result: MutableList<AgentDto>? = null


        try {

            resultApi = service.getAgents()
        } catch (e: IOException) {

            Log.e("TAG", e.message.toString())

        } catch (e: HttpException) {

            Log.e("TAG", e.message.toString())

        }

        if (resultApi != null) {

            addAgents(resultApi.data.map { it.toAgentEntity() }.toMutableList())
        }



        return getAllAgentsdb()

    }




    override suspend fun addAgents(agents: MutableList<AgentEntity>) {

        agentDao.insertAgents(agents)
    }

    override suspend fun getAllAgentsdb(): MutableList<AgentModel>? {

        return agentDao.getAllAgents()?.map { it.toAgentModel() }?.toMutableList()
    }

    override suspend fun getAgentByRole(role: String): MutableList<AgentModel>? {

        return agentDao.getAgentbyRole(role)?.map { it.toAgentModel() }?.toMutableList()

    }

    //debe devolver agentModel o AgentEntity
    override suspend fun getAgentById(id: String): AgentModel {

        return agentDao.getAgentById(id).toAgentModel()
    }
}