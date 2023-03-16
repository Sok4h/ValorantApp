package com.sokah.valorantapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.data.exceptions.CustomException
import com.sokah.valorantapp.data.repository.IAgentRepository
import com.sokah.valorantapp.data.repository.IPreferencesRepository
import com.sokah.valorantapp.ui.viewStates.AgentViewStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentListViewModel @Inject constructor(
    private val repository: IAgentRepository,
    private val preferencesRepository: IPreferencesRepository
) :
    ViewModel() {

    private val _viewState = MutableLiveData<AgentViewStates>()
    val viewState get() = _viewState

    private val _preference = MutableLiveData<Int>()
    val preference get() = _preference


    fun getAgents() {

        viewModelScope.launch {

            _viewState.postValue(AgentViewStates.Loading)
            val result = repository.getAllAgents()

            when {
                result.isSuccess -> {
                    _viewState.postValue(AgentViewStates.AgentListSuccess(result.getOrThrow()))
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
            if (result.isEmpty()) _viewState.postValue(AgentViewStates.Error(CustomException("No agent with this role found")))
            else _viewState.postValue(AgentViewStates.AgentListSuccess(result))
        }
    }


    fun getPreference() {

        viewModelScope.launch {

            preferencesRepository.getPreference().collect {
                _preference.postValue(it)


            }
        }

    }

    fun savePreference(value: Int) {

        viewModelScope.launch {
            preferencesRepository.savePreference(value)
        }
    }


}


