package com.test.weatheapp.data.repository

import com.test.weatheapp.data.model.WeatherEntity
import com.test.weatheapp.data.retrofit.model.GeoCoding


interface WeatherRetrofit {
    suspend fun getWheather(lat:Double, lon:Double, apiKey:String): WeatherEntity
    suspend fun getCityName(nameCity: String, apiKey: String): GeoCoding
}
