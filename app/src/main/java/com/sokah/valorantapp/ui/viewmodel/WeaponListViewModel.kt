package com.sokah.valorantapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.data.repository.IWeaponRepository
import com.sokah.valorantapp.data.repository.WeaponRepository
import com.sokah.valorantapp.ui.viewStates.WeaponViewState
import kotlinx.coroutines.launch

class WeaponListViewModel() : ViewModel() {

    private val _viewState = MutableLiveData<WeaponViewState>()
    val viewState get() = _viewState
    val repository: IWeaponRepository

    init {


        repository = WeaponRepository()
        getWeapons()


    }

    fun getWeapons() {

        viewModelScope.launch {

            _viewState.postValue(WeaponViewState.Loading)
            val result = repository.getAllWeapons()
            when {
                result.isSuccess -> _viewState.postValue(WeaponViewState.Success(result.getOrThrow()))
                else -> {
                    _viewState.postValue(WeaponViewState.Error(result.exceptionOrNull() as Exception))
                }
            }

        }


    }

    fun sortWeapon(type: String) {

        viewModelScope.launch {

            val response = repository.getWeaponByCategory(type)

            _viewState.postValue(WeaponViewState.Success(response))
        }


    }
}