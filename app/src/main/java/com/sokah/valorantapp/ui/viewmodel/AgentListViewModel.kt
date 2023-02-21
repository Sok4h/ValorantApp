package com.sokah.valorantapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.data.repository.AgentRepository
import com.sokah.valorantapp.data.repository.IAgentRepository
import com.sokah.valorantapp.ui.viewStates.AgentViewStates
import kotlinx.coroutines.launch

class AgentListViewModel() : ViewModel() {

    private val repository: IAgentRepository = AgentRepository()

    private val _viewState = MutableLiveData<AgentViewStates>()
    val viewState get() = _viewState

    init {

        getAgents()
    }


    fun getAgents() {


        viewModelScope.launch {

            _viewState.postValue(AgentViewStates.Loading)

            val result = repository.getAllAgents()

            when {
                result.isSuccess -> {
                    _viewState.postValue(AgentViewStates.Success(result.getOrThrow()))
                }
                else -> {

                    _viewState.postValue(AgentViewStates.Error(result.exceptionOrNull() as Exception))
                }
            }

        }


    }

    fun filterAgent(role: String) {
        viewModelScope.launch {
            val result = repository.getAgentByRole(role)
            _viewState.postValue(AgentViewStates.Success(result))
        }
    }


}


