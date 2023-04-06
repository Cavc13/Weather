package com.snusnu.weathernordclan.data.network

import com.snusnu.weathernordclan.data.network.dto.ResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ForecastService {

    @GET(FORECAST)
    suspend fun getThreeDaysForecast(
        @Query(CITY) city: String,
        @Query(DAYS) days: String = COUNT_DAYS,
        @Header(HEADER_KEY) headerKey: String = MY_TOKEN,
        @Header(HEADER_HOST) headerHost: String = WEATHER_HOST,
    ): Response<ResponseDto>

    companion object {
        private const val FORECAST = "forecast.json?"
        private const val HEADER_KEY = "X-RapidAPI-Key"
        private const val HEADER_HOST = "X-RapidAPI-Host"
        private const val MY_TOKEN = "c43f2e5a7dmsh217c03437bb3210p141e40jsn0e384d0c214c"
        private const val WEATHER_HOST = "weatherapi-com.p.rapidapi.com"
        private const val CITY = "q"
        private const val DAYS = "days"
        private const val COUNT_DAYS = "3"
    }
}