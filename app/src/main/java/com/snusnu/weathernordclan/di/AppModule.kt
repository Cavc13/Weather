package com.snusnu.weathernordclan.di

import com.snusnu.weathernordclan.data.database.ForecastDao
import com.snusnu.weathernordclan.data.mapper.ForecastMapper
import com.snusnu.weathernordclan.data.network.ForecastService
import com.snusnu.weathernordclan.data.repository.WeatherRepositoryImpl
import com.snusnu.weathernordclan.domain.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(
        forecastService: ForecastService,
        forecastDao: ForecastDao,
        forecastMapper: ForecastMapper
    ): WeatherRepository {
        return WeatherRepositoryImpl(
            forecastService,
            forecastDao,
            forecastMapper
        )
    }
}