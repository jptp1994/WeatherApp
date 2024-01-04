package com.test.weatheapp.domain.model

data class Cord(
    val longitude:Double,
    val latitude:Double)

data class General(
    val temp:Double,
    val pressure: Int,
    val humidity: Int,
)
data class WeatherCity(
    val description:String,
    val icon:String
)
data class Wind(
    val speed:Double,
)

