package com.snusnu.weathernordclan.ui.forecast_day

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.snusnu.weathernordclan.databinding.FragmentForecastDayBinding
import com.snusnu.weathernordclan.ui.forecast_day.rv_hour.HourAdapter
import com.snusnu.weathernordclan.utils.Const
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForecastDayFragment : Fragment() {

    private var _binding: FragmentForecastDayBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<ForecastDayFragmentArgs>()
    private val forecastDayViewModel: ForecastDayViewModel by viewModels()
    private lateinit var hourAdapter: HourAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForecastDayBinding.inflate(inflater, container, false)
        forecastDayViewModel.getForecastDay(args.city, args.date)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hourAdapter = HourAdapter()
        binding.rvHours.adapter = hourAdapter
        setDayInfo()
    }

    private fun setDayInfo() {
        lifecycleScope.launch {
            forecastDayViewModel.citySharedFlow.collect { forecastDay ->
                binding.tvDate.text = forecastDay?.date.toString()
                binding.tvAverageCurrentTemp.text = "${forecastDay?.averageTemp}${Const.CELSIUS}"
                binding.tvMaxTemp.text = "${Const.MAX}${forecastDay?.maxTemp}${Const.CELSIUS}"
                binding.tvMinTemp.text = "${Const.MIN}${forecastDay?.minTemp}${Const.CELSIUS}"
                binding.tvSpeedCurrentWind.text = "${forecastDay?.windSpeed}${Const.SPEED}"
                binding.tvPercentCurrentHumidity.text = "${forecastDay?.humidity}%"
                Glide.with(requireActivity())
                    .load("${Const.HTTPS}${forecastDay?.icon}")
                    .into(binding.ivCurrentIcon)
                hourAdapter.submitList(forecastDay?.hours)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}