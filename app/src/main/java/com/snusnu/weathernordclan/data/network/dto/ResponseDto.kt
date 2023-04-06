package com.snusnu.weathernordclan.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.snusnu.weathernordclan.data.database.models.ForecastCityDbModel
import com.snusnu.weathernordclan.data.database.models.ForecastDayDbModel
import com.snusnu.weathernordclan.data.database.models.ForecastHourDbModel
import com.snusnu.weathernordclan.domain.ForecastHour

data class ResponseDto(
    @SerializedName("current")
    @Expose
    val currentDto: CurrentDto,

    @SerializedName("forecast")
    @Expose
    val forecastDto: ForecastDto,

    @SerializedName("location")
    @Expose
    val location: LocationDto
) {
    fun toForecastCityDbModel(): ForecastCityDbModel {
        return ForecastCityDbModel(
            location.name,
            currentDto.lastUpdated,
            currentDto.tempC,
            forecastDto.forecastdayDto[0].dayDto.mintempC,
            forecastDto.forecastdayDto[0].dayDto.maxtempC,
            currentDto.humidity,
            currentDto.conditionDto.icon,
            currentDto.windKph
        )
    }

    fun toListForecastDayDbModel(): List<ForecastDayDbModel> {
        return mutableListOf<ForecastDayDbModel>().apply {
            addAll(
                forecastDto.forecastdayDto.map {
                    ForecastDayDbModel(
                        ID,
                        it.date,
                        location.name,
                        it.dayDto.avgtempC,
                        it.dayDto.mintempC,
                        it.dayDto.maxtempC,
                        it.dayDto.avghumidity,
                        it.dayDto.conditionDto.icon,
                        it.dayDto.maxwindKph
                    )
                }
            )
        }
    }

    fun toListForecastHourDbModel(): List<ForecastHourDbModel> {
        val listHour = mutableListOf<ForecastHourDbModel>()

        forecastDto.forecastdayDto.forEach { forecastdayDto ->
            forecastdayDto.hourDto.forEach { hourDto ->
                listHour.add(
                    ForecastHourDbModel(
                        ID,
                        location.name,
                        hourDto.time,
                        forecastdayDto.date,
                        hourDto.tempC,
                        hourDto.conditionDto.icon
                    )
                )
            }
        }

        return listHour
    }

    companion object {
        private const val ID = 0L
    }
}