package com.sokah.valorantapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.codingwithmitch.food2forkcompose.presentation.util.ConnectionLiveData
import com.sokah.valorantapp.db.ValorantDatabase
import com.sokah.valorantapp.model.agents.AgentModel
import com.sokah.valorantapp.network.ValorantApiService
import com.sokah.valorantapp.repository.AgentRepository
import kotlinx.coroutines.launch

class AgentListViewModel(application: Application) : AndroidViewModel(application) {

    private var service = ValorantApiService()
    val mutableAgentList = MutableLiveData<MutableList<AgentModel>>()
     var agents: MutableList<AgentModel>?=null
    val repository : AgentRepository
    /*lateinit var internetConnection: MutableLiveData<Boolean>*/
    var connectionLiveData= ConnectionLiveData(application)
    val isLoading = MutableLiveData<Boolean>()

    init {

        val agentDao = ValorantDatabase.getInstance(application).agentDao()

        repository= AgentRepository(agentDao)
        var result: MutableList<AgentModel>?
        viewModelScope.launch {


            isLoading.postValue(true)
            result = repository.getAllAgents()
            Log.e("Response", result.toString() )
            isLoading.postValue(false)

            if (result!=null) {
                mutableAgentList.postValue(result!!)
                agents = result!!
            }

        }

    }

    fun getAgents() {

        mutableAgentList.postValue(agents)

    }

    fun filterAgent(role: String) {


        viewModelScope.launch {

            val result = service.getAgents()

            result!!.data.filter { it.role.displayName.contentEquals(role) }.also {

                result.data = it.toMutableList()
                mutableAgentList.postValue(result.data!!)
            }


        }


    }

}
