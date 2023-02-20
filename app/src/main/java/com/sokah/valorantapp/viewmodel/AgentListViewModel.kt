package com.sokah.valorantapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.db.ValorantDatabase
import com.sokah.valorantapp.model.agents.AgentModel
import com.sokah.valorantapp.repository.AgentRepository
import com.sokah.valorantapp.repository.IAgentRepository
import kotlinx.coroutines.launch

class AgentListViewModel():ViewModel() {

    val mutableAgentList = MutableLiveData<MutableList<AgentModel>?>()
    var agents: MutableList<AgentModel>? = null
    private val repository: IAgentRepository = AgentRepository()
    val isLoading = MutableLiveData<Boolean>()

    init {

        getAgents()
    }


    fun getAgents() {


        viewModelScope.launch{

            isLoading.postValue(true)
            var result: MutableList<AgentModel>? = repository.getAllAgents()
            isLoading.postValue(false)

            if (result != null) {
                mutableAgentList.postValue(result)

            }

        }


    }

    suspend fun filterAgent(role: String) {

        val result = repository.getAgentByRole(role)

        Log.e("TAG", result!!.size.toString())
        mutableAgentList.postValue(result)


    }


}


