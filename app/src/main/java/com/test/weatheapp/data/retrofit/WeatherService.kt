package com.test.weatheapp.data.retrofit
import com.test.weatheapp.data.retrofit.model.GeoCodingList
import com.test.weatheapp.data.retrofit.model.WeatherClass
import retrofit2.http.GET
import retrofit2.http.Query

//Contains the method available for retrofit layer
interface WeatherService {
    @GET("data/2.5/weather")
    suspend fun getWhether(@Query("lat") lat:Double, @Query("lon") long:Double, @Query("appid") apiKey:String)
    : WeatherClass

    @GET("geo/1.0/direct")
    suspend fun getCityName(@Query("q") cityName:String, @Query("appid") apiKey:String)
    : List<GeoCodingList>

}
