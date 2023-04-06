package com.snusnu.weathernordclan.ui.forecast_city

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.snusnu.weathernordclan.R
import com.snusnu.weathernordclan.databinding.FragmentForecastCityBinding
import com.snusnu.weathernordclan.domain.ForecastCity
import com.snusnu.weathernordclan.ui.forecast_city.rv_day.DayAdapter
import com.snusnu.weathernordclan.utils.Const.CELSIUS
import com.snusnu.weathernordclan.utils.Const.EMPTY_TEXT
import com.snusnu.weathernordclan.utils.Const.FOR_INITIALIZATION_FLOW
import com.snusnu.weathernordclan.utils.Const.HTTPS
import com.snusnu.weathernordclan.utils.Const.MAX
import com.snusnu.weathernordclan.utils.Const.MIN
import com.snusnu.weathernordclan.utils.Const.NOT_FIND_CITY
import com.snusnu.weathernordclan.utils.Const.OFFLINE
import com.snusnu.weathernordclan.utils.Const.SPEED
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForecastCityFragment : Fragment() {

    private var _binding: FragmentForecastCityBinding? = null
    private val binding get() = _binding!!
    private val forecastCityViewModel: ForecastCityViewModel by viewModels()
    private lateinit var dayAdapter: DayAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForecastCityBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dayAdapter = DayAdapter()
        binding.rvThreeDays.adapter = dayAdapter
        setForecastCityListeners()

        lifecycleScope.launch {
            forecastCityViewModel.cityStateFlow.collect { forecastCity ->
                viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                    if (forecastCity != null && forecastCity.city != FOR_INITIALIZATION_FLOW) {
                        setCurrentInfo(forecastCity)
                        dayAdapter.submitList(forecastCity.days)
                    }
                }
            }
        }
    }

    private fun setForecastCityListeners() {
        setOnDayClickListener()
        setSearchListener()
    }

    private fun setOnDayClickListener() {
        dayAdapter.onDayClickListener = object : DayAdapter.OnDayClickListener {
            override fun onDayClick(city: String, date: String) {
                launchForecastDayFragment(city, date)
            }
        }
    }

    private fun launchForecastDayFragment(city: String, date: String) {
        findNavController().navigate(
            ForecastCityFragmentDirections
                .actionForecastCityFragmentToForecastDayFragment(city, date)
        )
    }

    private fun setSearchListener() {
        binding.ivSearch.setOnClickListener {
            if (binding.etSearchCity.text.isNullOrBlank()) {
                binding.etSearchCity.error = EMPTY_TEXT
            } else {
                if (isOnline()) {
                    forecastCityViewModel.getForecastCity(
                        binding.etSearchCity.text.toString().trim()
                    )
                    lifecycleScope.launch {
                        forecastCityViewModel.cityStateFlow.collect { forecastCity ->
                            if (forecastCity == null) {
                                closeInfo(NOT_FIND_CITY)
                            } else if (forecastCity.city == FOR_INITIALIZATION_FLOW) {

                            } else {
                                setCurrentInfo(forecastCity)
                                dayAdapter.submitList(forecastCity.days)
                            }
                        }
                    }
                } else {
                    closeInfo(OFFLINE)
                }
            }
        }
    }

    private fun closeInfo(valueInfo: String) {
        binding.ivCurrentIcon.setImageResource(R.drawable.ic_mood_bad_24)
        binding.tvAverageCurrentTemp.text = valueInfo
        binding.group.visibility = View.INVISIBLE
        dayAdapter.submitList(emptyList())
    }

    private fun setCurrentInfo(forecastCity: ForecastCity) {
        binding.group.visibility = View.VISIBLE
        binding.tvAverageCurrentTemp.text = "${forecastCity.averageTemp}${CELSIUS}"
        binding.tvMaxTemp.text = "${MAX}${forecastCity.maxTemp}${CELSIUS}"
        binding.tvMinTemp.text = "${MIN}${forecastCity.minTemp}${CELSIUS}"
        binding.tvSpeedCurrentWind.text = "${forecastCity.windSpeed}${SPEED}"
        binding.tvPercentCurrentHumidity.text = "${forecastCity.humidity}%"
        Glide.with(requireActivity())
            .load("${HTTPS}${forecastCity.icon}")
            .into(binding.ivCurrentIcon)
    }

    private fun isOnline(): Boolean {
        val connectivityManager =
            requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(
                connectivityManager.activeNetwork
            ) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            if (connectivityManager.activeNetworkInfo != null &&
                connectivityManager.activeNetworkInfo!!.isConnectedOrConnecting
            ) {
                return true
            }
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}