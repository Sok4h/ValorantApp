package com.sokah.valorantapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.sokah.valorantapp.model.agents.AgentModel
import com.sokah.valorantapp.network.ValorantApiService
import kotlinx.coroutines.launch
import java.util.*

class AgentDetailViewModel(agentUuid:String) : ViewModel() {

    private var service = ValorantApiService()
    val agentDetail = MutableLiveData <AgentModel>()



    init {

        viewModelScope.launch{

            lateinit var languageCode :String

            languageCode = when (Locale.getDefault().language){

                "es"-> "es-ES"

                else -> "en-US"
            }



            val result = service.getAgent(agentUuid,languageCode)

            val agent = result.data

            for((index,ability) in agent.abilities.withIndex()) {

                if(ability.slot.contentEquals("Passive")){

                    agent.abilities.removeAt(index)
                }
            }
            agentDetail.postValue(agent)
        }
    }


}