package com.sokah.valorantapp.ui.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.data.repository.AgentRepository
import com.sokah.valorantapp.ui.mapper.uiModel.AgentModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentDetailViewModel @Inject constructor(private val repository: AgentRepository) :
    ViewModel() {

    val agentDetail = MutableLiveData<AgentModel>()
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