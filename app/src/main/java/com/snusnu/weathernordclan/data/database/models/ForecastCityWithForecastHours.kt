package com.snusnu.weathernordclan.data.database.models

import androidx.room.Embedded
import androidx.room.Relation

data class ForecastCityWithForecastHours(
    @Embedded val cityDbModel: ForecastCityDbModel,
    @Relation(
        parentColumn = "city",
        entityColumn = "city"
    )
    val hourDbModels: List<ForecastHourDbModel>
)