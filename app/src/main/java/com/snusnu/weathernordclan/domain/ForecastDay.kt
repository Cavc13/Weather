package com.snusnu.weathernordclan.domain

data class ForecastDay(
    val date: String,
    val city: String,
    val averageTemp: Double,
    val minTemp: Double,
    val maxTemp: Double,
    val humidity: Double,
    val icon: String,
    val windSpeed: Double,
    val hours: List <ForecastHour>
)