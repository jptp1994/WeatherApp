package com.test.weatheapp.data.room.mapper

import com.test.weatheapp.data.model.WeatherEntity
import com.test.weatheapp.data.model.generalModels.CordEntity
import com.test.weatheapp.data.model.generalModels.GeneralEntity
import com.test.weatheapp.data.model.generalModels.WindEntity
import com.test.weatheapp.data.room.model.WeatherRoomClass
import javax.inject.Inject

//Mapper from Weather Room to Weather Entity
class WeatherRoomMapper @Inject constructor(
    private val generalRoomMapper: GeneralRoomMapper,
    private val cordRoomMapper: CordRoomMapper,
    private val windRoomMapper: WindRoomMapper,
    private val weatherCityRoomMapper: WeatherCityRoomMapper,
) : CacheMapper<WeatherRoomClass?, WeatherEntity> {
    override fun mapFromCached(type: WeatherRoomClass?): WeatherEntity {
        return WeatherEntity(
            cordEntity = type?.cord?.let { cordRoomMapper.mapFromCached(it) } ?: CordEntity(0.0,0.0),
            weatherCityEntity = type?.weatherCityClass?.map { weatherCityRoomMapper.mapFromCached(it) }?: listOf(),
            generalEntity = type?.generalClass?.let { generalRoomMapper.mapFromCached(it) } ?: GeneralEntity(0.0,0,0),
            windEntity = type?.wind?.let { windRoomMapper.mapFromCached(it) } ?: WindEntity(0.0),
            nameCity = type?.nameCity?:"",
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
