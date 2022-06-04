package com.sokah.valorantapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.db.ValorantDatabase

import com.sokah.valorantapp.model.agents.AgentModel
import com.sokah.valorantapp.network.ValorantApiService
import com.sokah.valorantapp.repository.AgentRepository
import kotlinx.coroutines.launch
import java.util.*

class AgentDetailViewModel(agentUuid:String,application: Application) : AndroidViewModel(application) {

    private var service = ValorantApiService()
    val agentDetail = MutableLiveData <AgentModel>()

    val repository: AgentRepository

    init {

        val agentDao = ValorantDatabase.getInstance(application).agentDao()
        repository = AgentRepository(agentDao)
        viewModelScope.launch{





            //val result = service.getAgent(agentUuid,languageCode)

            val agent = repository.getAgentById(agentUuid)

            for((index,ability) in agent.abilities.withIndex()) {

                if(ability.slot.contentEquals("Passive")){

                    agent.abilities.removeAt(index)
                }
            }
            agentDetail.postValue(agent)
        }
    }


}