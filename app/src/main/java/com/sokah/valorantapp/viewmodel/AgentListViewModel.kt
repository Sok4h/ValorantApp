package com.sokah.valorantapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.network.ValorantApiService
import com.sokah.valorantapp.model.agents.AgentModel
import com.sokah.valorantapp.model.BaseModel
import kotlinx.coroutines.launch

class AgentListViewModel : ViewModel() {

    private var service = ValorantApiService()
    val mutableAgentList = MutableLiveData <BaseModel<MutableList<AgentModel>>>()
    lateinit var agents : BaseModel<MutableList<AgentModel>>
    init {

        viewModelScope.launch {

            val result= service.getAgents()

            if(result!=null){

                //filtra que no salga sova npc
                result.data.filter{ it.isPlayableCharacter }.also{

                    result.data=it.toMutableList()
                    mutableAgentList.postValue(result)
                    agents=result
                }

            }

        }

    }

    fun getAgents(){

        mutableAgentList.postValue(agents)
      /*  viewModelScope.launch {

            val result= service.getAgents()

            if(result!=null){
                var agents = mutableAgentList
                mutableAgentList.postValue(result!!)
            }

        }*/

    }
    fun filterAgent(role:String){

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