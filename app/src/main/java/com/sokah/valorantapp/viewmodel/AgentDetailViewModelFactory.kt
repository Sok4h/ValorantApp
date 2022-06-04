package com.sokah.valorantapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AgentDetailViewModelFactory (private var agentUuid:String,private var application: Application): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(AgentDetailViewModel::class.java)){

           return AgentDetailViewModel(agentUuid,application) as T
        }
        throw IllegalArgumentException("viewModel not valid")
    }
}