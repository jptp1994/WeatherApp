package com.test.weatheapp.data.retrofit.repository

import com.test.weatheapp.data.model.WeatherEntity
import com.test.weatheapp.data.repository.WeatherRetrofit
import com.test.weatheapp.data.retrofit.WeatherService
import com.test.weatheapp.data.retrofit.mapper.WeatherRetrofitMapper
import com.test.weatheapp.data.retrofit.model.GeoCodingList
import javax.inject.Inject


//Contains the instance of the repository of retrofit
class WeatherRetrofitImp @Inject constructor(
    private val weatherService: WeatherService,
    private val weatherRetrofitMapper: WeatherRetrofitMapper
): WeatherRetrofit {

    //Get the city by name
    override suspend fun getCityName(nameCity: String, apiKey: String): List<GeoCodingList> {
        return weatherService.getCityName(nameCity,apiKey)
    }

    //Get the city by a latitude a longitude
    override suspend fun getWheather(lat:Double, lon:Double, apiKey:String): WeatherEntity {
        return weatherRetrofitMapper.mapFromModel(weatherService.getWhether(lat,lon,apiKey))
    }
}
