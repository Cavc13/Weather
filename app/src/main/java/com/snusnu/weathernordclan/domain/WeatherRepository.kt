package com.snusnu.weathernordclan.domain

interface WeatherRepository {

    suspend fun getForecastCityFromNetwork(city: String): ForecastCity?

    suspend fun getForecastDayFromData(city: String?, date: String?): ForecastDay?
}