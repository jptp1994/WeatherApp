package com.test.weatheapp.data.model.generalModels

import com.google.gson.annotations.SerializedName

data class CordEntity(
    @SerializedName("lon")
    val longitude:Double,
    @SerializedName("lat")
    val latitude:Double)

data class GeneralEntity(
    @SerializedName("temp")
    val temp:Double,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("humidity")
    val humidity: Int,
)
data class WeatherCityEntity(
    @SerializedName("description")
    val description:String,
    @SerializedName("icon")
    val icon:String
)
data class WindEntity(
    @SerializedName("speed")
    val speed:Double,
)

