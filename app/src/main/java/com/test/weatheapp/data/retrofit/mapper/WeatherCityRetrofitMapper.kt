package com.test.weatheapp.data.retrofit.mapper

import com.test.weatheapp.data.model.generalModels.WeatherCityEntity
import com.test.weatheapp.data.retrofit.model.WeatherCityClass
import javax.inject.Inject

//Mapper from WeatherCity Retrofit to WeatherCity Entity
class WeatherCityRetrofitMapper @Inject constructor() :
    EntityMapper<WeatherCityClass, WeatherCityEntity> {


    override fun mapFromModel(model: WeatherCityClass): WeatherCityEntity {
        return WeatherCityEntity(description = model.description, icon = model.icon)
    }
}
