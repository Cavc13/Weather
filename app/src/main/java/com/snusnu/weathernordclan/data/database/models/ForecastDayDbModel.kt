package com.snusnu.weathernordclan.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast_day")
data class ForecastDayDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val date: String,
    val city: String,
    @ColumnInfo(name = "average_temp") val averageTemp: Double,
    @ColumnInfo(name = "min_temp") val minTemp: Double,
    @ColumnInfo(name = "max_temp") val maxTemp: Double,
    val humidity: Double,
    val icon: String,
    @ColumnInfo(name = "wind_speed") val windSpeed: Double
)