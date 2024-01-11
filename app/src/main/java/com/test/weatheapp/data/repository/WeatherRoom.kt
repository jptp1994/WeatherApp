package com.test.weatheapp.data.repository

import com.test.weatheapp.data.model.WeatherEntity
import com.test.weatheapp.data.room.model.WeatherRoomClass

//Contains the methods for room
interface WeatherRoom {

    //Used for get all the information of city inside of the database room
    suspend fun getAllWeather(): List<WeatherEntity>

    // Get a specific weather city by the filter of a name of city
    suspend fun getSelectedWeather(nameCity:String): WeatherEntity

    // Create a new weather city
    suspend fun saveSelectedWeather(weatherRoomClass: WeatherRoomClass)

    // Update the information of a city get by retrofit
    suspend fun updateSelectedWeather(weatherRoomClass: WeatherRoomClass)
  }


