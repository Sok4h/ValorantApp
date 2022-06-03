package com.sokah.valorantapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwithmitch.food2forkcompose.presentation.util.ConnectionLiveData
import com.sokah.valorantapp.model.BaseModel
import com.sokah.valorantapp.model.agents.AgentModel
import com.sokah.valorantapp.network.ValorantApiService
import com.sokah.valorantapp.repository.AgentRepository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class AgentListViewModel(application: Application) : AndroidViewModel(application) {

    private var service = ValorantApiService()
    val mutableAgentList = MutableLiveData<MutableList<AgentModel>>()
     var agents: MutableList<AgentModel>?=null
    var repository = AgentRepository()
    /*lateinit var internetConnection: MutableLiveData<Boolean>*/
    var connectionLiveData= ConnectionLiveData(application)
    val isLoading = MutableLiveData<Boolean>()

    init {

         var result: BaseModel<MutableList<AgentModel>>?=null
        viewModelScope.launch {


            isLoading.postValue(true)
            result = repository.getAllAgents()
            isLoading.postValue(false)

            if (result!=null) {
                mutableAgentList.postValue(result!!.data!!)
                agents = result!!.data
            }

        }

    }

    fun getAgents() {

        mutableAgentList.postValue(agents)

    }

    fun filterAgent(role: String) {


        viewModelScope.launch {

            val result = service.getAgents()

            result!!.data.filter { it.role.displayName.contentEquals(role) }.also {

                result.data = it.toMutableList()
                mutableAgentList.postValue(result.data!!)
            }


        }


    }

}
