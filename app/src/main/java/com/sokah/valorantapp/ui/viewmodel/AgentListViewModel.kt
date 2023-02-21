package com.sokah.valorantapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.ui.mapper.uiModel.AgentModel
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
            Log.e("Camilo", result.toString() )

            //como manejo la logica de errores de internet mas los de room
            when {
                result.isSuccess -> {
                    _viewState.postValue(AgentViewStates.AllAgents(result.getOrThrow()))
                }
                else -> {

                    _viewState.postValue(AgentViewStates.Error)
                }
            }

        }


    }

    suspend fun filterAgent(role: String) {

        val result = repository.getAgentByRole(role)
        _viewState.postValue(AgentViewStates.AllAgents(result))
    }


}


