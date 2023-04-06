package com.snusnu.weathernordclan.domain

data class ForecastHour(
    val time: String,
    val date: String,
    val temp: Double,
    val icon: String
)