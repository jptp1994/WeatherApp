package com.test.weatheapp.data.retrofit
import com.test.weatheapp.data.retrofit.model.GeoCodingResponse
import com.test.weatheapp.data.retrofit.model.WeatherClass
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherService {
    @GET("data/2.5/weather")
    suspend fun getWhether(@Query("lat") lat:Double, @Query("lon") long:Double, @Query("appid") apiKey:String)
    : WeatherClass

    @GET("geo/1.0/direct")
    suspend fun getCityName(@Query("q") cityName:String,@Query("limit") limit:String, @Query("appid") apiKey:String)
    : GeoCodingResponse

}
