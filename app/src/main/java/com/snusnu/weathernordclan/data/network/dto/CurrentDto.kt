package com.snusnu.weathernordclan.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.snusnu.weathernordclan.data.database.models.ForecastCityDbModel

data class CurrentDto(
    @SerializedName("cloud")
    @Expose
    val cloud: Int,

    @SerializedName("condition")
    @Expose
    val conditionDto: ConditionDto,

    @SerializedName("feelslike_c")
    @Expose
    val feelslikeC: Double,

    @SerializedName("feelslike_f")
    @Expose
    val feelslikeF: Double,

    @SerializedName("gust_kph")
    @Expose
    val gustKph: Double,

    @SerializedName("gust_mph")
    @Expose
    val gustMph: Double,

    @SerializedName("humidity")
    @Expose
    val humidity: Int,

    @SerializedName("is_day")
    @Expose
    val isDay: Int,

    @SerializedName("last_updated")
    @Expose
    val lastUpdated: String,

    @SerializedName("last_updated_epoch")
    @Expose
    val lastUpdated_epoch: Int,

    @SerializedName("precip_in")
    @Expose
    val precipIn: Double,

    @SerializedName("precip_mm")
    @Expose
    val precipMm: Double,

    @SerializedName("pressure_in")
    @Expose
    val pressureIn: Double,

    @SerializedName("pressure_mb")
    @Expose
    val pressureMb: Double,

    @SerializedName("temp_c")
    @Expose
    val tempC: Double,

    @SerializedName("temp_f")
    @Expose
    val tempF: Double,

    @SerializedName("uv")
    @Expose
    val uv: Double,

    @SerializedName("vis_km")
    @Expose
    val visKm: Double,

    @SerializedName("vis_miles")
    @Expose
    val visMiles: Double,

    @SerializedName("wind_degree")
    @Expose
    val windDegree: Int,

    @SerializedName("wind_dir")
    @Expose
    val windDir: String,

    @SerializedName("wind_kph")
    @Expose
    val windKph: Double,

    @SerializedName("wind_mph")
    @Expose
    val windMph: Double
)