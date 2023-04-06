package com.snusnu.weathernordclan.ui.forecast_city.rv_day

import androidx.recyclerview.widget.DiffUtil
import com.snusnu.weathernordclan.domain.ForecastDay

object DayDiffCallback : DiffUtil.ItemCallback<ForecastDay>() {
    override fun areItemsTheSame(oldItem: ForecastDay, newItem: ForecastDay): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(oldItem: ForecastDay, newItem: ForecastDay): Boolean {
        return oldItem == newItem
    }
}