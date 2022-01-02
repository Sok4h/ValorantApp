package com.sokah.valorantapp.model.weapons


import com.google.gson.annotations.SerializedName

data class DamageRange(
    @SerializedName("bodyDamage")
    val bodyDamage: Double,
    @SerializedName("headDamage")
    val headDamage: Double,
    @SerializedName("legDamage")
    val legDamage: Double,
    @SerializedName("rangeEndMeters")
    val rangeEndMeters: Double,
    @SerializedName("rangeStartMeters")
    val rangeStartMeters: Double
)