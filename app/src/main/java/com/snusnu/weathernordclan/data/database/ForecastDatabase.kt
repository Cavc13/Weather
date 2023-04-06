package com.snusnu.weathernordclan.data.database

import androidx.room.*
import com.snusnu.weathernordclan.data.database.models.ForecastCityDbModel
import com.snusnu.weathernordclan.data.database.models.ForecastDayDbModel
import com.snusnu.weathernordclan.data.database.models.ForecastHourDbModel

@Database(
    entities = [ForecastCityDbModel::class, ForecastDayDbModel::class, ForecastHourDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class ForecastDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "forecasts.db"
    }

    abstract fun forecastDao(): ForecastDao
}