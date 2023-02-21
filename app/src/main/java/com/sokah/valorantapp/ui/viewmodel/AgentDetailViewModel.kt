package com.sokah.valorantapp.ui.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.ui.mapper.uiModel.AgentModel
import com.sokah.valorantapp.data.repository.AgentRepository
import com.sokah.valorantapp.data.repository.IAgentRepository
import kotlinx.coroutines.launch

class AgentDetailViewModel() :
    ViewModel() {

    val agentDetail = MutableLiveData<AgentModel>()

    val repository: IAgentRepository = AgentRepository()


    fun getAgentDetail(agentUuid: String) {

        viewModelScope.launch {

            //val result = service.getAgent(agentUuid,languageCode)

            val agent = repository.getAgentById(agentUuid)

            for ((index, ability) in agent.abilities.withIndex()) {

                if (ability.slot.contentEquals("Passive")) {

                    agent.abilities.removeAt(index)
                }
            }
            agentDetail.postValue(agent)
        }
    }


}