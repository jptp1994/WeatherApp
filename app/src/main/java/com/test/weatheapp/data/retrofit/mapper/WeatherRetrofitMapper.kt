package com.test.weatheapp.data.retrofit.mapper

import com.test.weatheapp.data.model.WeatherEntity
import com.test.weatheapp.data.retrofit.model.WeatherClass
import javax.inject.Inject

class WeatherRetrofitMapper @Inject constructor(
    private val weatherCityRetrofitMapper: WeatherCityRetrofitMapper,
    private val generalRetrofitMapper: GeneralRetrofitMapper,
    private val windRetrofitMapper: WindRetrofitMapper,
) : EntityMapper<WeatherClass, WeatherEntity> {
    override fun mapFromModel(model: WeatherClass): WeatherEntity {
        return WeatherEntity(
            cordEntity= model.cordEntity,
            weatherCityEntity=model.weatherCityClass.map { weatherCityRetrofitMapper.mapFromModel(it)  },
            generalEntity=generalRetrofitMapper.mapFromModel(model.generalClass),
            windEntity= windRetrofitMapper.mapFromModel(model.windClass),
            nameCity=model.nameCity
        )
    }
}
