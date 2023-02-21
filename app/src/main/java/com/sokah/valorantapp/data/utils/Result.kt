package com.sokah.valorantapp.data.utils

sealed class Result<out R> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Failure<out T>(val exception: Exception) : Result<T>()
    object Loading : Result<Nothing>()
}