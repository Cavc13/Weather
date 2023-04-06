package com.snusnu.weathernordclan.data.repository

import com.snusnu.weathernordclan.data.database.ForecastDao
import com.snusnu.weathernordclan.data.mapper.ForecastMapper
import com.snusnu.weathernordclan.data.network.ForecastService
import com.snusnu.weathernordclan.domain.ForecastCity
import com.snusnu.weathernordclan.domain.ForecastDay
import com.snusnu.weathernordclan.domain.WeatherRepository
import com.snusnu.weathernordclan.utils.*
import com.snusnu.weathernordclan.utils.Const.MINIMUM_WAITING_TIME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val forecastService: ForecastService,
    private val forecastDao: ForecastDao,
    private val forecastMapper: ForecastMapper
) : WeatherRepository {

    override suspend fun getForecastCityFromNetwork(city: String): ForecastCity? =
        withContext(Dispatchers.IO) {
            val forecastCityDb = forecastDao.getForecastCity(city)
            val listForecastDayDb =
                forecastDao.getForecastCityWithForecastDays(city)?.dayDbModels
            val listForecastHourDb =
                forecastDao.getForecastCityWithForecastHours(city)?.hourDbModels

            if (forecastCityDb == null) {
                getNetworkResponse(city)
            } else {
                val date = forecastCityDb.getDate()
                val hour = forecastCityDb.getHour()
                val minutes = forecastCityDb.getMinutes()

                if (date != getCurrentDate()) {
                    getNetworkResponse(city)
                } else if (hour != getCurrentHour()) {
                    getNetworkResponse(city)
                } else if (getCurrentMinutes() - minutes > MINIMUM_WAITING_TIME) {
                    getNetworkResponse(city)
                } else {
                    if (listForecastDayDb != null && listForecastHourDb != null) {
                        forecastMapper.mapForecastModelsToForecastCity(
                            forecastCityDb,
                            listForecastDayDb,
                            listForecastHourDb
                        )
                    } else {
                        null
                    }
                }
            }
        }

    private suspend fun getNetworkResponse(city: String): ForecastCity? {
        val responseDto = forecastService.getThreeDaysForecast(city).body()

        return if (responseDto != null) {
            val forecastCityDbModel = responseDto.toForecastCityDbModel()
            forecastDao.insertForecastCity(forecastCityDbModel)
            val listForecastDayDbModel = responseDto.toListForecastDayDbModel()
            listForecastDayDbModel.forEach {
                forecastDao.insertForecastDay(it)
            }
            val listForecastHourDbModel = responseDto.toListForecastHourDbModel()
            listForecastHourDbModel.forEach {
                forecastDao.insertForecastHour(it)
            }
            forecastMapper.mapForecastModelsToForecastCity(
                forecastCityDbModel,
                listForecastDayDbModel,
                listForecastHourDbModel
            )
        } else {
            null
        }
    }

    override suspend fun getForecastDayFromData(city: String?, date: String?): ForecastDay? =
        withContext(Dispatchers.IO) {
            if (city != null && date != null) {
                val listForecastDayDb =
                    forecastDao.getForecastCityWithForecastDays(city)?.dayDbModels
                val listForecastHourDb =
                    forecastDao.getForecastCityWithForecastHours(city)?.hourDbModels
                if (listForecastDayDb != null && listForecastHourDb != null) {
                    forecastMapper.mapForecastModelsToForecastDay(
                        date,
                        listForecastDayDb,
                        listForecastHourDb
                    )
                } else {
                    null
                }
            } else {
                null
            }
        }
}