package com.test.weatheapp.data.source


import com.test.weatheapp.data.model.WeatherEntity
import com.test.weatheapp.data.repository.WeatherDataSource
import com.test.weatheapp.data.repository.WeatherRoom
import com.test.weatheapp.data.retrofit.model.GeoCoding
import com.test.weatheapp.data.room.model.WeatherRoomClass
import javax.inject.Inject

class WeatherCacheDataSource @Inject constructor(
    private val weatherCache: WeatherRoom
) : WeatherDataSource {


    override suspend fun getWeather(lat: Double, lon: Double, apiKey: String): WeatherEntity {
        throw UnsupportedOperationException("getWheather is not supported for CacheDataSource.")
    }

    override suspend fun getCityName(nameCity: String, apiKey: String): GeoCoding {
        throw UnsupportedOperationException("getCityName is not supported for CacheDataSource.")
    }

    override suspend fun getAllWeather(): List<WeatherEntity> {
        return weatherCache.getAllWeather()
    }

    override suspend fun getSelectedWeather(nameCity: String): WeatherEntity {
        return weatherCache.getSelectedWeather(nameCity)
    }

    override suspend fun saveSelectedWeather(weatherRoomClass: WeatherRoomClass) {
        return weatherCache.saveSelectedWeather(weatherRoomClass)
    }

    override suspend fun updateSelectedWeather(weatherRoomClass: WeatherRoomClass) {
        return weatherCache.updateSelectedWeather(weatherRoomClass)
    }
}
