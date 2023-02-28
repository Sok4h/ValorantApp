package com.sokah.valorantapp.data.model

data class BaseModel<T>(
    val status: Int,
    var data: T,
    val exception: Exception
)