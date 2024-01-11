package com.test.weatheapp.data.repository

import com.test.weatheapp.data.model.WeatherEntity
import com.test.weatheapp.data.retrofit.model.GeoCodingList
import com.test.weatheapp.data.room.model.WeatherRoomClass


//Repository for general methods from repository
interface WeatherDataSource {

    //Retrofit
    suspend fun getWeather(lat:Double, lon:Double, apiKey:String): WeatherEntity
    suspend fun getCityName(nameCity: String, apiKey: String): List<GeoCodingList>

    //Room
    suspend fun getAllWeather(): List<WeatherEntity>
    suspend fun getSelectedWeather(nameCity:String): WeatherEntity
    suspend fun saveSelectedWeather(weatherRoomClass: WeatherRoomClass)
    suspend fun updateSelectedWeather(weatherRoomClass: WeatherRoomClass)
}
