package com.test.weatheapp.data.retrofit

import com.test.weatheapp.data.retrofit.model.GeoCoding
import com.test.weatheapp.data.retrofit.model.WeatherClass
import retrofit2.http.GET
import retrofit2.http.Path


interface WeatherService {

    @GET("data/2.5/weather?lat={id}&lon={lon}&appid={api_key}")
    suspend fun getWhether(@Path("lat") lat:Double, @Path("lon") long:Double, @Path("api_key") apiKey:String)
    : WeatherClass


    @GET("geo/1.0/direct?q={city_name}&limit=1&appid={api_key}")
    suspend fun getCityName(@Path("city_name") cityName:String, @Path("api_key") apiKey:String)
    : GeoCoding

}
