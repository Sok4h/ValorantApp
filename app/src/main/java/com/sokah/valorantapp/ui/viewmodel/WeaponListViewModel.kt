package com.sokah.valorantapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sokah.valorantapp.data.repository.IWeaponRepository
import com.sokah.valorantapp.ui.viewStates.WeaponViewState
import com.sokah.valorantapp.utils.IdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeaponListViewModel @Inject constructor(private val repository: IWeaponRepository) :
    ViewModel() {

    private val _viewState = MutableLiveData<WeaponViewState>()
    val viewState get() = _viewState

    init {
        getWeapons()
    }

    fun getWeapons() {

        viewModelScope.launch {

            _viewState.postValue(WeaponViewState.Loading)
            IdlingResource.increment()
            val result = repository.getAllWeapons()
            IdlingResource.decrement()
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