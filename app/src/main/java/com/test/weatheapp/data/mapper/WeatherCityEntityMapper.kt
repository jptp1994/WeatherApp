package com.test.weatheapp.data.mapper

import com.test.weatheapp.data.model.generalModels.WeatherCityEntity
import com.test.weatheapp.domain.model.WeatherCity
import javax.inject.Inject

//Mapper from WeatherCity Entity from WeatherCity Model
class WeatherCityEntityMapper @Inject constructor() :
    EntityMapper<WeatherCityEntity, WeatherCity> {

    override fun mapFromModel(model: WeatherCityEntity): WeatherCity {
        return WeatherCity(description = model.description, icon = model.icon)

    }
}
