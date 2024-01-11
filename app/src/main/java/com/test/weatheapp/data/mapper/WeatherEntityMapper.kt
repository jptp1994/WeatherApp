package com.test.weatheapp.data.mapper

import com.test.weatheapp.data.model.WeatherEntity
import com.test.weatheapp.domain.model.Weather
import javax.inject.Inject

//Mapper from Weather Entity to Weather Model
class WeatherEntityMapper @Inject constructor(
    private val cordEntityMapper: CordEntityMapper,
    private val generalEntityMapper: GeneralEntityMapper,
    private val weatherCityEntityMapper: WeatherCityEntityMapper,
    private val windEntityMapper: WindEntityMapper,
) : EntityMapper<WeatherEntity, Weather> {


    override fun mapFromModel(model: WeatherEntity): Weather {
        return Weather(
             cord= cordEntityMapper.mapFromModel(model.cordEntity),
             weatherCity = model.weatherCityEntity.map { weatherCityEntityMapper.mapFromModel(it)  },
             general = generalEntityMapper.mapFromModel(model.generalEntity),
             wind = windEntityMapper.mapFromEntity(model.windEntity),
             nameCity= model.nameCity
        )
    }
}
