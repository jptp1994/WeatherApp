package com.test.weatheapp.data.retrofit.mapper

import com.test.weatheapp.data.model.WeatherEntity
import com.test.weatheapp.data.model.generalModels.CordEntity
import com.test.weatheapp.data.model.generalModels.GeneralEntity
import com.test.weatheapp.data.model.generalModels.WindEntity
import com.test.weatheapp.data.retrofit.model.WeatherClass
import javax.inject.Inject

//Mapper from Weather Retrofit to Weather Entity
class WeatherRetrofitMapper @Inject constructor(
    private val weatherCityRetrofitMapper: WeatherCityRetrofitMapper,
    private val generalRetrofitMapper: GeneralRetrofitMapper,
    private val windRetrofitMapper: WindRetrofitMapper,
    private val cordRetrofitMapper: CordRetrofitMapper,
) : EntityMapper<WeatherClass, WeatherEntity> {
    override fun mapFromModel(model: WeatherClass): WeatherEntity {
        return WeatherEntity(
            cordEntity= model.coord?.let { cordRetrofitMapper.mapFromModel(it) }?:CordEntity(0.0,0.0),
            weatherCityEntity=model.weather?.map { weatherCityRetrofitMapper.mapFromModel(it)}?: listOf(),
            generalEntity= model.main?.let { generalRetrofitMapper.mapFromModel(it) } ?: GeneralEntity(temp = 0.0,
                pressure = 0, humidity = 0
            ),
            windEntity= model.wind?.let { windRetrofitMapper.mapFromModel(it) }?: WindEntity(0.0),
            nameCity=model.name?:""
        )
    }
}
