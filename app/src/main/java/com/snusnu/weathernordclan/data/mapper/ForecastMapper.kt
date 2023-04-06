package com.snusnu.weathernordclan.data.mapper

import com.snusnu.weathernordclan.data.database.models.ForecastCityDbModel
import com.snusnu.weathernordclan.data.database.models.ForecastDayDbModel
import com.snusnu.weathernordclan.data.database.models.ForecastHourDbModel
import com.snusnu.weathernordclan.domain.ForecastCity
import com.snusnu.weathernordclan.domain.ForecastDay
import javax.inject.Inject

class ForecastMapper @Inject constructor() {

    fun mapForecastModelsToForecastCity(
        forecastCityDbModel: ForecastCityDbModel,
        listForecastDayDbModel: List<ForecastDayDbModel>,
        listForecastHourDbModel: List<ForecastHourDbModel>
    ): ForecastCity {
        with(forecastCityDbModel) {
            return ForecastCity(
                city, averageTemp, minTemp, maxTemp, humidity, icon, windSpeed,
                listForecastDayDbModel.map { fDayDbModel ->
                    ForecastDay(
                        date = fDayDbModel.date,
                        city = fDayDbModel.city,
                        averageTemp = fDayDbModel.averageTemp,
                        minTemp = fDayDbModel.minTemp,
                        maxTemp = fDayDbModel.maxTemp,
                        humidity = fDayDbModel.humidity,
                        icon = fDayDbModel.icon,
                        windSpeed = fDayDbModel.windSpeed,
                        listForecastHourDbModel.map { fHourDbModel ->
                            fHourDbModel.toForecastHour()
                        }
                    )
                }
            )
        }
    }

    fun mapForecastModelsToForecastDay(
        date: String,
        listForecastDayDbModel: List<ForecastDayDbModel>,
        listForecastHourDbModel: List<ForecastHourDbModel>
    ): ForecastDay {
        val listForecastHours = listForecastHourDbModel.filter {
            it.date == date
        }.map {
            it.toForecastHour()
        }

        val forecastDay = listForecastDayDbModel.filter {
            it.date == date
        }.map { fDayDbModel ->
            ForecastDay(
                date = fDayDbModel.date,
                city = fDayDbModel.city,
                averageTemp = fDayDbModel.averageTemp,
                minTemp = fDayDbModel.minTemp,
                maxTemp = fDayDbModel.maxTemp,
                humidity = fDayDbModel.humidity,
                icon = fDayDbModel.icon,
                windSpeed = fDayDbModel.windSpeed,
                hours = listForecastHours
            )
        }[0]

        return forecastDay
    }
}