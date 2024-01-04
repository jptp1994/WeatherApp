package com.test.weatheapp.data.repository

import com.test.weatheapp.data.model.WeatherEntity
import com.test.weatheapp.data.room.model.WeatherRoomClass


interface WeatherRoom {
    suspend fun getAllWeather(): List<WeatherEntity>
    suspend fun getSelectedWeather(nameCity:String): WeatherEntity
    suspend fun saveSelectedWeather(weatherRoomClass: WeatherRoomClass)
    suspend fun updateSelectedWeather(weatherRoomClass: WeatherRoomClass)
  }


