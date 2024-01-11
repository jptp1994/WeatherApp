package com.test.weatheapp.data.retrofit.model

data class WeatherClass(
    val coord: CordRetrofit?,
    val weather: List<WeatherCityClass>?,
    val main: GeneralClass?,
    val wind:WindClass?,
    val name:String?
)

data class CordRetrofit(
    val lon:Double,
    val lat:Double)

data class GeneralClass(
    val temp:Double,
    val feels_like:Double,
    val temp_min:Double,
    val temp_max:Double,
    val pressure: Int,
    val humidity: Int,
)
data class WeatherCityClass(
    val id:Int,
    val main:String,
    val description:String,
    val icon:String
)
data class WindClass(
    val speed:Double,
    val deg:Int,
)
