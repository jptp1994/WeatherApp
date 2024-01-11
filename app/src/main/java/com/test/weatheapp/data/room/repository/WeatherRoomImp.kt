package com.test.weatheapp.data.room.repository

import com.test.weatheapp.data.model.WeatherEntity
import com.test.weatheapp.data.repository.WeatherRoom
import com.test.weatheapp.data.room.dao.WeatherDao
import com.test.weatheapp.data.room.mapper.WeatherRoomMapper
import com.test.weatheapp.data.room.model.WeatherRoomClass
import javax.inject.Inject
import javax.inject.Singleton


//Contains the instance of the repository of room
@Singleton
class WeatherRoomImp @Inject constructor(
    private val weatherService: WeatherDao,
    private val weatherRoomMapper: WeatherRoomMapper
) : WeatherRoom {

    //Get all the weather in room

    override suspend fun getAllWeather(): List<WeatherEntity> {
        return weatherService.getAllWeather().map {
            weatherRoomMapper.mapFromCached(it)
        }
    }

    // Get the weather filter by name

    override suspend fun getSelectedWeather(nameCity: String): WeatherEntity {
        return weatherRoomMapper.mapFromCached(weatherService.getSelectedWeather(nameCity))
    }

    //Insert a new register in weather database

    override suspend fun saveSelectedWeather(weatherRoomClass: WeatherRoomClass) {
        weatherService.saveSelectedWeather(weatherRoomClass)
    }

    //Update a existing city with the information from retrofit

    override suspend fun updateSelectedWeather(weatherRoomClass: WeatherRoomClass) {
        weatherService.updateSelectedWeather(weatherRoomClass)
    }


}
