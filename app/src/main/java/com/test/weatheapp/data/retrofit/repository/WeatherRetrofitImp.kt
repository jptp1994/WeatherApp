package com.test.weatheapp.data.retrofit.repository

import com.test.weatheapp.data.model.WeatherEntity
import com.test.weatheapp.data.repository.WeatherRetrofit
import com.test.weatheapp.data.retrofit.WeatherService
import com.test.weatheapp.data.retrofit.mapper.WeatherRetrofitMapper
import com.test.weatheapp.data.retrofit.model.GeoCoding
import javax.inject.Inject

class WeatherRetrofitImp @Inject constructor(
    private val weatherService: WeatherService,
    private val weatherRetrofitMapper: WeatherRetrofitMapper
): WeatherRetrofit {

    override suspend fun getCityName(nameCity: String, apiKey: String): GeoCoding {
        return weatherService.getCityName(nameCity,apiKey)
    }

    override suspend fun getWheather(lat:Double, lon:Double, apiKey:String): WeatherEntity {
        return weatherRetrofitMapper.mapFromModel(weatherService.getWhether(lat,lon,apiKey))
    }
}
