package com.snusnu.weathernordclan.di

import android.app.Application
import androidx.room.Room
import com.snusnu.weathernordclan.data.database.ForecastDao
import com.snusnu.weathernordclan.data.database.ForecastDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideForecastDatabase(
        application: Application
    ): ForecastDatabase {

        return Room.databaseBuilder(
            application,
            ForecastDatabase::class.java,
            ForecastDatabase.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideForecastDao(
        forecastDatabase: ForecastDatabase
    ): ForecastDao {
        return forecastDatabase.forecastDao()
    }
}