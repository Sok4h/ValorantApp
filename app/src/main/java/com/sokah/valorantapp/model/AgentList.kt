package com.sokah.valorantapp.model


import com.google.gson.annotations.SerializedName
import com.sokah.valorantapp.model.AgentModel

data class AgentList(
    @SerializedName("data")
    val agents: List<AgentModel>,
    @SerializedName("status")
    val status: Int
)