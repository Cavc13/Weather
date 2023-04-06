package com.snusnu.weathernordclan.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ConditionDto(
    @SerializedName("code")
    @Expose
    val code: Int,

    @SerializedName("icon")
    @Expose
    val icon: String,

    @SerializedName("text")
    @Expose
    val text: String
)