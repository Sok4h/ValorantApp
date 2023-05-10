package com.sokah.valorantapp.ui.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.data.repository.AgentRepository
import com.sokah.valorantapp.ui.viewStates.AgentViewStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentDetailViewModel @Inject constructor(private val repository: AgentRepository) :
    ViewModel() {

    private val _viewState = MutableLiveData<AgentViewStates>()
    val viewState get() = _viewState

    fun getAgentDetail(agentUuid: String) {

        viewModelScope.launch {

            val result = repository.getAgentById(agentUuid)

            if (result.isSuccess) {

                val agent = result.getOrThrow()

                for ((index, ability) in agent.abilities.withIndex()) {
                    if (ability.slot.contentEquals("Passive")) {
                        agent.abilities.removeAt(index)
                    }
                }

                _viewState.postValue(AgentViewStates.AgentSuccess(agent))

            } else {

                _viewState.postValue(AgentViewStates.Error(result.exceptionOrNull() as Exception))

            }


        }
    }


}