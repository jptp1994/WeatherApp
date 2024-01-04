package com.test.weatheapp.data.room.mapper

import com.test.weatheapp.data.model.WeatherEntity
import com.test.weatheapp.data.room.model.WeatherRoomClass
import javax.inject.Inject

class WeatherRoomMapper @Inject constructor(
    private val generalRoomMapper: GeneralRoomMapper,
    private val cordRoomMapper: CordRoomMapper,
    private val windRoomMapper: WindRoomMapper,
    private val weatherCityRoomMapper: WeatherCityRoomMapper,
) : CacheMapper<WeatherRoomClass, WeatherEntity> {
    override fun mapFromCached(type: WeatherRoomClass): WeatherEntity {
        return WeatherEntity(
            cordEntity = cordRoomMapper.mapFromCached(type.cord),
            weatherCityEntity = type.weatherCityClass.map { weatherCityRoomMapper.mapFromCached(it) },
            generalEntity = generalRoomMapper.mapFromCached(type.generalClass),
            windEntity = windRoomMapper.mapFromCached(type.wind),
            nameCity = type.nameCity,
            )
    }

    override fun mapToCached(type: WeatherEntity): WeatherRoomClass {
        return WeatherRoomClass(
            cord = cordRoomMapper.mapToCached(type.cordEntity),
            weatherCityClass = type.weatherCityEntity.map { weatherCityRoomMapper.mapToCached(it) },
            generalClass = generalRoomMapper.mapToCached(type.generalEntity),
            wind = windRoomMapper.mapToCached(type.windEntity),
            nameCity = type.nameCity,
            )
    }
}
