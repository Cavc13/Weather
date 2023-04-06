package com.snusnu.weathernordclan.data.database.models

import androidx.room.Embedded
import androidx.room.Relation

data class ForecastCityWithForecastDays(
    @Embedded val cityDbModel: ForecastCityDbModel,
    @Relation(
        parentColumn = "city",
        entityColumn = "city"
    )
    val dayDbModels: List<ForecastDayDbModel>,
)
