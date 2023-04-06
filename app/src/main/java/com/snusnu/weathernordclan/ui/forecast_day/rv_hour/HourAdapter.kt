package com.snusnu.weathernordclan.ui.forecast_day.rv_hour

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.snusnu.weathernordclan.databinding.HourItemBinding
import com.snusnu.weathernordclan.domain.ForecastHour
import com.snusnu.weathernordclan.utils.Const.CELSIUS
import com.snusnu.weathernordclan.utils.Const.CHOOSE_TIME
import com.snusnu.weathernordclan.utils.Const.HTTPS
import com.snusnu.weathernordclan.utils.Const.SPLIT_DAY_AND_TIME

class HourAdapter : ListAdapter<ForecastHour, HourViewHolder>(HourDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourViewHolder {
        return HourViewHolder(
            HourItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HourViewHolder, position: Int) {
        val currentHour = getItem(position)

        with(holder.binding) {
            with(currentHour) {
                tvHour.text = time.split(SPLIT_DAY_AND_TIME)[CHOOSE_TIME]
                tvHourTemp.text = "$temp${CELSIUS}"
                Glide.with(holder.itemView.context)
                    .load("${HTTPS}$icon")
                    .into(ivHourIcon)
            }
        }
    }
}