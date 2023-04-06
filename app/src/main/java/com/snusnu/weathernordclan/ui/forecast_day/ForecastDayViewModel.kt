package com.snusnu.weathernordclan.ui.forecast_day

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.snusnu.weathernordclan.domain.ForecastDay
import com.snusnu.weathernordclan.domain.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastDayViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _daySharedFlow = MutableSharedFlow<ForecastDay?>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    val citySharedFlow: Flow<ForecastDay?> = _daySharedFlow.distinctUntilChanged()

    fun getForecastDay(city: String?, date: String?) {
        viewModelScope.launch {
            _daySharedFlow.tryEmit(weatherRepository.getForecastDayFromData(city, date))
        }
    }
}