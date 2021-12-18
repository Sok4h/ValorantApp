package com.sokah.valorantapp.prueba


import com.google.gson.annotations.SerializedName

data class AgentTest(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: Int
)