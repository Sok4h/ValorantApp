package com.sokah.valorantapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.network.ValorantApiService
import com.sokah.valorantapp.model.AgentModel
import com.sokah.valorantapp.model.BaseModel
import kotlinx.coroutines.launch

class AgentListViewModel : ViewModel() {

    private var service = ValorantApiService()
    val agentList = MutableLiveData <BaseModel<List<AgentModel>>>()

    init {

        viewModelScope.launch {

            val result= service.getAgents()

            if(result!=null){

                agentList.postValue(result)
            }

        }

    }
}