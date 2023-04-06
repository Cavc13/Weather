package com.snusnu.weathernordclan.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.snusnu.weathernordclan.utils.Const

@Entity(tableName = "forecast_city")
data class ForecastCityDbModel(
    @PrimaryKey(autoGenerate = false)
    val city: String,
    @ColumnInfo(name = "last_update") val lastUpdate: String,
    @ColumnInfo(name = "average_temp") val averageTemp: Double,
    @ColumnInfo(name = "min_temp") val minTemp: Double,
    @ColumnInfo(name = "max_temp") val maxTemp: Double,
    val humidity: Int,
    val icon: String,
    @ColumnInfo(name = "wind_speed") val windSpeed: Double
) {
    fun getDate() = lastUpdate.split(Const.SPLIT_DAY_AND_TIME)[Const.CHOOSE_DAY]

    fun getHour() = lastUpdate.split(Const.SPLIT_DAY_AND_TIME)[Const.CHOOSE_TIME]
                              .split(Const.SPLIT_HOUR_AND_MIN)[Const.CHOOSE_HOUR]
                              .toInt()

    fun getMinutes() = lastUpdate.split(Const.SPLIT_DAY_AND_TIME)[Const.CHOOSE_TIME]
                                 .split(Const.SPLIT_HOUR_AND_MIN)[Const.CHOOSE_MIN]
                                 .toInt()
}
