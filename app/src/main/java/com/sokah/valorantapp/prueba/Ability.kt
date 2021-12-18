package com.sokah.valorantapp.prueba


import com.google.gson.annotations.SerializedName

data class Ability(
    @SerializedName("description")
    val description: String,
    @SerializedName("displayIcon")
    val displayIcon: String,
    @SerializedName("displayName")
    val displayName: String,
    @SerializedName("slot")
    val slot: String
)