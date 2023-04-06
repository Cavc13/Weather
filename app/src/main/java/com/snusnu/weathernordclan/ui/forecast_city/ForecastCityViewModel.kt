package com.snusnu.weathernordclan.ui.forecast_city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snusnu.weathernordclan.domain.ForecastCity
import com.snusnu.weathernordclan.domain.WeatherRepository
import com.snusnu.weathernordclan.utils.Const.FOR_INITIALIZATION_FLOW
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastCityViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _cityStateFlow = MutableStateFlow<ForecastCity?>(initializationForecastCity)
    val cityStateFlow: Flow<ForecastCity?> = _cityStateFlow

    fun getForecastCity(city: String) {
        viewModelScope.launch {
            _cityStateFlow.value  = weatherRepository.getForecastCityFromNetwork(city)
        }
    }

    companion object {
        private val initializationForecastCity = ForecastCity(
            FOR_INITIALIZATION_FLOW,
            0.0,
            0.0,
            0.0,
            0,
            FOR_INITIALIZATION_FLOW,
            0.0,
            emptyList()
        )
    }
}