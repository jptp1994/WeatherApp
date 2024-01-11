package com.test.weatheapp.data.room.mapper

import com.test.weatheapp.data.model.generalModels.WeatherCityEntity
import com.test.weatheapp.data.retrofit.model.WeatherCityClass
import javax.inject.Inject

//Mapper from WeatherCity Room to WeatherCity Entity
class WeatherCityRoomMapper @Inject constructor() :
    CacheMapper<WeatherCityClass, WeatherCityEntity> {


    override fun mapFromCached(type: WeatherCityClass): WeatherCityEntity {
        return WeatherCityEntity(
            description = type.description,
            icon = type.icon
        )
    }

    override fun mapToCached(type: WeatherCityEntity): WeatherCityClass {
        return WeatherCityClass(
            description = type.description,
            icon = type.icon,
            id = 0,
            main = ""
        )
    }
}
