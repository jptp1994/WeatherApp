package com.test.weatheapp.domain.model

data class Weather(
    val cord: Cord,
    val weatherCity: List<WeatherCity>,
    val general: General,
    val wind: Wind,
    val nameCity:String
)
