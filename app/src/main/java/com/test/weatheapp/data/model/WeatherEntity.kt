package com.test.weatheapp.data.model

import com.google.gson.annotations.SerializedName
import com.test.weatheapp.data.model.generalModels.CordEntity
import com.test.weatheapp.data.model.generalModels.GeneralEntity
import com.test.weatheapp.data.model.generalModels.WeatherCityEntity
import com.test.weatheapp.data.model.generalModels.WindEntity

data class WeatherEntity(
    @SerializedName("coord")
    val cordEntity: CordEntity,
    @SerializedName("weather")
    val weatherCityEntity: List<WeatherCityEntity>,
    @SerializedName("main")
    val generalEntity: GeneralEntity,
    @SerializedName("wind")
    val windEntity: WindEntity,
    @SerializedName("name")
    var nameCity:String
)

