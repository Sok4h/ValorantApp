package com.sokah.valorantapp.repository

import android.util.Log
import com.sokah.valorantapp.db.AgentDao
import com.sokah.valorantapp.model.BaseModel
import com.sokah.valorantapp.model.agents.AgentModel
import com.sokah.valorantapp.network.ValorantApiService
import retrofit2.HttpException
import java.io.IOException

class AgentRepository(private val agentDao: AgentDao) {

    private var service = ValorantApiService()


    suspend fun getAllAgents(): MutableList<AgentModel>? {

        var resultApi: BaseModel<MutableList<AgentModel>>? = null
        var result: MutableList<AgentModel>? = null

        // var allBooks: LiveData<MutableList<Book>>

        try {

            resultApi = service.getAgents()
        } catch (e: IOException) {

            Log.e("TAG", e.message.toString())

        } catch (e: HttpException) {

            Log.e("TAG", e.message.toString())

        }

        if (resultApi != null) {

            addAgents(resultApi.data)
        }



        return getAllAgentsdb()

    }


    suspend fun addAgents(agents: MutableList<AgentModel>) {

        agentDao.insert(agents)
    }

    suspend fun getAllAgentsdb(): MutableList<AgentModel>? {

        return agentDao.getAllAgents()
    }

    suspend fun getAgentByRole(role:String):MutableList<AgentModel>?{

        return agentDao.getAgentbyRole(role)

    }
}