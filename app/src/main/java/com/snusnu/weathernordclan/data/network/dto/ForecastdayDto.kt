package com.snusnu.weathernordclan.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.snusnu.weathernordclan.data.database.models.ForecastHourDbModel

data class ForecastdayDto(
    @SerializedName("astro")
    @Expose
    val astroDto: AstroDto,

    @SerializedName("date")
    @Expose
    val date: String,

    @SerializedName("date_epoch")
    @Expose
    val dateEpoch: Int,

    @SerializedName("day")
    @Expose
    val dayDto: DayDto,

    @SerializedName("hour")
    @Expose
    val hourDto: List<HourDto>
) {

    fun toListForecastHourDbModel(city: String): List<ForecastHourDbModel> {
        return mutableListOf<ForecastHourDbModel>().apply {
            addAll(
                hourDto.map {
                    ForecastHourDbModel(
                        ID,
                        city,
                        it.time.split(SPLIT_DAY_AND_TIME)[TIME],
                        it.time.split(SPLIT_DAY_AND_TIME)[DAY],
                        it.tempC,
                        it.conditionDto.icon
                    )
                }
            )
        }
    }

    companion object{
        private const val ID = 0L
        private const val SPLIT_DAY_AND_TIME = " "
        private const val DAY = 0
        private const val TIME = 1
    }
}