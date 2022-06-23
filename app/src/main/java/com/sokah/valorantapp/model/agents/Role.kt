package com.sokah.valorantapp.model.agents


import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class Role(
   /* @SerializedName("assetPath")
    val assetPath: String,*/
    @SerializedName("description")
    val roleDescription: String,
    @SerializedName("displayIcon")
    val roleIcon: String,
    @SerializedName("displayName")
    val roleName: String,
    @SerializedName("uuid")
    val roleuuid: String
)