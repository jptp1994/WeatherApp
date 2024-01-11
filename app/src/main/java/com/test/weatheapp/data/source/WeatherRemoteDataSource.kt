package com.test.weatheapp.data.source

import com.test.weatheapp.data.model.WeatherEntity
import com.test.weatheapp.data.repository.WeatherDataSource
import com.test.weatheapp.data.repository.WeatherRetrofit
import com.test.weatheapp.data.retrofit.model.GeoCodingList
import com.test.weatheapp.data.room.model.WeatherRoomClass
import javax.inject.Inject


//Data source for retrofit
class WeatherRemoteDataSource @Inject constructor(
    private val weatherRemote: WeatherRetrofit
) : WeatherDataSource {


    override suspend fun getWeather(lat: Double, lon: Double, apiKey: String): WeatherEntity {
       return weatherRemote.getWheather(lat, lon, apiKey)
    }

    override suspend fun getCityName(nameCity: String, apiKey: String): List<GeoCodingList> {
       return  weatherRemote.getCityName(nameCity,apiKey)
    }

    override suspend fun getAllWeather(): List<WeatherEntity> {
        throw UnsupportedOperationException("getAllWeather is not supported for RemoteDataSource.")
    }

    override suspend fun getSelectedWeather(nameCity: String): WeatherEntity {
        throw UnsupportedOperationException("getSelectedWeather is not supported for RemoteDataSource.")
    }

    override suspend fun saveSelectedWeather(weatherRoomClass: WeatherRoomClass) {
        throw UnsupportedOperationException("saveSelectedWeather is not supported for RemoteDataSource.")
    }

    override suspend fun updateSelectedWeather(weatherRoomClass: WeatherRoomClass) {
        throw UnsupportedOperationException("updateSelectedWeather is not supported for RemoteDataSource.")
    }
}
