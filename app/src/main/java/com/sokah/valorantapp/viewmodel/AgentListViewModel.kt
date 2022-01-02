package com.sokah.valorantapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.network.ValorantApiService
import com.sokah.valorantapp.model.AgentModel
import com.sokah.valorantapp.model.BaseModel
import kotlinx.coroutines.launch

class AgentListViewModel : ViewModel() {

    private var service = ValorantApiService()
    val mutableAgentList = MutableLiveData <BaseModel<MutableList<AgentModel>>>()

    init {

        viewModelScope.launch {

            val result= service.getAgents()

            if(result!=null){
                var agents = mutableAgentList
                mutableAgentList.postValue(result!!)
            }

        }

    }

    fun filterAgent(role:String){

   /*  var data = Transformations.map(mutableAgentList){
         it.data.filter { it.role.displayName.contentEquals(role)}
     }
    mutableAgentList.postValue(data)*/
        viewModelScope.launch {

            val result= service.getAgents()

            result.data.filter{ it.isPlayableCharacter }.also {

                it.filter { it.role.displayName.contentEquals(role)}.also {

                    result.data=it.toMutableList()
                    mutableAgentList.postValue(result)
                }



            }


        }

    }
}