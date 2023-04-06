package com.snusnu.weathernordclan.ui.forecast_day.rv_hour

import androidx.recyclerview.widget.DiffUtil
import com.snusnu.weathernordclan.domain.ForecastHour

object HourDiffCallback : DiffUtil.ItemCallback<ForecastHour>() {
    override fun areItemsTheSame(oldItem: ForecastHour, newItem: ForecastHour): Boolean {
        return oldItem.time == newItem.time
    }

    override fun areContentsTheSame(oldItem: ForecastHour, newItem: ForecastHour): Boolean {
        return oldItem == newItem
    }
}