package com.snusnu.weathernordclan.ui.forecast_city.rv_day

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.snusnu.weathernordclan.databinding.DayItemBinding
import com.snusnu.weathernordclan.domain.ForecastDay
import com.snusnu.weathernordclan.utils.Const
import com.snusnu.weathernordclan.utils.Const.CELSIUS

class DayAdapter : ListAdapter<ForecastDay, DayViewHolder>(DayDiffCallback) {

    var onDayClickListener: OnDayClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        return DayViewHolder(
            DayItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val currentDay = getItem(position)

        with(holder.binding) {
            with(currentDay) {
                tvDate.text = date
                tvAverageTemp.text = "$averageTemp$CELSIUS"
                tvMaxTemp.text = "$maxTemp$CELSIUS"
                tvMinTemp.text = "$minTemp$CELSIUS"
                Glide.with(holder.itemView.context)
                    .load("${Const.HTTPS}$icon")
                    .into(ivIconDay)

                root.setOnClickListener {
                    onDayClickListener?.onDayClick(city, date)
                }
            }
        }
    }

    interface OnDayClickListener {
        fun onDayClick(city: String, date: String)
    }
}