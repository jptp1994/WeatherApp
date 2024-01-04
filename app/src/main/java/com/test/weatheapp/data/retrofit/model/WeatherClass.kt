package com.test.weatheapp.data.retrofit.model

import com.google.gson.annotations.SerializedName
import com.test.weatheapp.data.model.generalModels.CordEntity

data class WeatherClass(
    @SerializedName("coord")
    val cordEntity: CordEntity,
    @SerializedName("weather")
    val weatherCityClass: List<WeatherCityClass>,
    @SerializedName("main")
    val generalClass: GeneralClass,
    @SerializedName("wind")
    val windClass:WindClass,
    @SerializedName("name")
    val nameCity:String
)

data class GeneralClass(
    @SerializedName("temp")
    val temp:Double,
    @SerializedName("feels_like")
    val feelsLike:Double,
    @SerializedName("temp_min")
    val tempMin:Double,
    @SerializedName("temp_max")
    val tempMax:Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("sea_level")
    val seaLevel: Int,
    @SerializedName("grnd_level")
    val grndLevel: Int
)
data class WeatherCityClass(
    @SerializedName("id")
    val id:Int,
    @SerializedName("main")
    val main:String,
    @SerializedName("description")
    val description:String,
    @SerializedName("icon")
    val icon:String
)
data class WindClass(
    @SerializedName("speed")
    val speed:Double,
    @SerializedName("deg")
    val deg:Int,
    @SerializedName("gust")
    val gust:Double
)
