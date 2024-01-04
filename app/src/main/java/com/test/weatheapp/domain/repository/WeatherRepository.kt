package com.test.weatheapp.domain.repository

import android.content.Context
import com.test.weatheapp.domain.model.Weather
import com.test.weatheapp.domain.model.WeatherParams
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    // Remote and cache
    suspend fun getWeathers(): Flow<List<Weather>>
    suspend fun getWeather(weatherParams:WeatherParams): Flow<Weather>
}
