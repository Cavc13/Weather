package com.snusnu.weathernordclan.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.snusnu.weathernordclan.domain.ForecastHour

@Entity(tableName = "forecast_hour")
data class ForecastHourDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val city: String,
    val time: String,
    val date: String,
    val temp: Double,
    val icon: String
) {
    fun toForecastHour() = ForecastHour(
        time, date, temp, icon
    )
}