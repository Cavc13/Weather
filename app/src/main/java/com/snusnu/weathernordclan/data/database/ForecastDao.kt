package com.snusnu.weathernordclan.data.database

import androidx.room.*
import com.snusnu.weathernordclan.data.database.models.*

@Dao
interface ForecastDao {
    @Query("SELECT * FROM forecast_city WHERE city == :city")
    suspend fun getForecastCity(city: String): ForecastCityDbModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecastCity(forecastCity: ForecastCityDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecastDay(forecastDay: ForecastDayDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertForecastHour(forecastHour: ForecastHourDbModel)

    @Transaction
    @Query("SELECT * FROM forecast_city WHERE city = :city")
    fun getForecastCityWithForecastDays(city: String): ForecastCityWithForecastDays?

    @Transaction
    @Query("SELECT * FROM forecast_city WHERE city = :city")
    fun getForecastCityWithForecastHours(city: String): ForecastCityWithForecastHours?
}