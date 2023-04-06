package com.snusnu.weathernordclan.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ForecastDto(
    @SerializedName("forecastday")
    @Expose
    val forecastdayDto: List<ForecastdayDto>
)