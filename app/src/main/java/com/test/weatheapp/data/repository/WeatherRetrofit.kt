package com.test.weatheapp.data.repository

import com.test.weatheapp.data.model.WeatherEntity
import com.test.weatheapp.data.retrofit.model.GeoCodingList

//Contains the methods for retrofit
interface WeatherRetrofit {

    //Get the information from a latitude and longitude specific
    suspend fun getWheather(lat:Double, lon:Double, apiKey:String): WeatherEntity

    //Get the information from a city by the name use in the search bar
    suspend fun getCityName(nameCity: String, apiKey: String): List<GeoCodingList>
}
