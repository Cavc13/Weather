package com.snusnu.weathernordclan.domain

data class ForecastCity(
    val city: String,
    val averageTemp: Double,
    val minTemp: Double,
    val maxTemp: Double,
    val humidity: Int,
    val icon: String,
    val windSpeed: Double,
    val days: List<ForecastDay>
)