package com.sokah.valorantapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AgentDetailViewModelFactory (private var agentUuid:String): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(AgentDetailViewModel::class.java)){

           return AgentDetailViewModel(agentUuid) as T
        }
        throw IllegalArgumentException("viewModel not valid")
    }
}