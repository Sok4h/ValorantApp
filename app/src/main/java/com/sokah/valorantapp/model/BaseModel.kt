package com.sokah.valorantapp.model

data class BaseModel<T>(
    val status: Int,
    val data: T
)