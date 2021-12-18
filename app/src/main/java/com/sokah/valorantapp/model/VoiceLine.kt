package com.sokah.valorantapp.model


import com.google.gson.annotations.SerializedName

data class VoiceLine(
    @SerializedName("maxDuration")
    val maxDuration: Double,
    @SerializedName("mediaList")
    val mediaList: List<Media>,
    @SerializedName("minDuration")
    val minDuration: Double
)