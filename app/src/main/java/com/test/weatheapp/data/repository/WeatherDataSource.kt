package com.test.weatheapp.data.repository

import com.test.weatheapp.data.model.WeatherEntity
import com.test.weatheapp.data.retrofit.model.GeoCoding
import com.test.weatheapp.data.room.model.WeatherRoomClass


interface WeatherDataSource {

    //Retrofit
    suspend fun getWeather(lat:Double, lon:Double, apiKey:String): WeatherEntity
    suspend fun getCityName(nameCity: String, apiKey: String): GeoCoding

    //Room
    suspend fun getAllWeather(): List<WeatherEntity>
    suspend fun getSelectedWeather(nameCity:String): WeatherEntity
    suspend fun saveSelectedWeather(weatherRoomClass: WeatherRoomClass)
    suspend fun updateSelectedWeather(weatherRoomClass: WeatherRoomClass)
}
