package com.sokah.valorantapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.db.ValorantDatabase
import com.sokah.valorantapp.model.agents.AgentModel
import com.sokah.valorantapp.network.ValorantApiService
import com.sokah.valorantapp.repository.AgentRepository
import com.sokah.valorantapp.utils.ConnectionLiveData
import kotlinx.coroutines.launch

class AgentListViewModel(application: Application) : AndroidViewModel(application) {

    val mutableAgentList = MutableLiveData<MutableList<AgentModel>?>()
    var agents: MutableList<AgentModel>? = null
    val repository: AgentRepository
    val isLoading = MutableLiveData<Boolean>()

    init {

        val agentDao = ValorantDatabase.getInstance(application).agentDao()
        repository = AgentRepository(agentDao)

        getAgents()

    }

    fun getAgents() {

        var result: MutableList<AgentModel>?
        viewModelScope.launch {

            isLoading.postValue(true)
            result = repository.getAllAgents()
            isLoading.postValue(false)

            if (result != null) {
                mutableAgentList.postValue(result!!)

            }

        }


    }

    suspend fun filterAgent(role: String) {

        val result = repository.getAgentByRole(role)

        Log.e("TAG", result!!.size.toString() )
        mutableAgentList.postValue(result)


    }


}


