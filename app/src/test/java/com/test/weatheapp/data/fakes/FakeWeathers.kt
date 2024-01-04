package com.test.weatheapp.data.fakes

import com.test.weatheapp.data.model.WeatherEntity
import com.test.weatheapp.data.model.generalModels.CordEntity
import com.test.weatheapp.data.model.generalModels.GeneralEntity
import com.test.weatheapp.data.model.generalModels.WeatherCityEntity
import com.test.weatheapp.data.model.generalModels.WindEntity
import com.test.weatheapp.domain.model.Cord
import com.test.weatheapp.domain.model.General
import com.test.weatheapp.domain.model.Weather
import com.test.weatheapp.domain.model.WeatherCity
import com.test.weatheapp.domain.model.Wind

object FakeWeathers {
    fun getWeathers(): List<WeatherEntity> = listOf(
        WeatherEntity(
            cordEntity = CordEntity(19.344123, -99.061062),
            weatherCityEntity = listOf(WeatherCityEntity(description = "Full sun", icon = "e10")),
            generalEntity =
            GeneralEntity(
                temp=22.86,
                pressure = 1023,
                humidity = 11,
           ), windEntity = WindEntity(
                speed = 0.0,
           ), nameCity = "Iztapalapa"
        ),
        WeatherEntity(
            cordEntity= CordEntity(19.344123, -99.061062),
            weatherCityEntity = listOf(WeatherCityEntity(description = "Full sun", icon = "e10")),
            generalEntity =
            GeneralEntity(
                temp=22.86,
                pressure = 1023,
                humidity = 11,
            ), windEntity = WindEntity(
                speed = 0.0,
            ), nameCity = "Caracas"
        )
    )

    fun getWeather(): Weather =
        Weather(
            cord= Cord(19.344123, -99.061062),
            weatherCity = listOf(WeatherCity(description = "Full sun", icon = "e10")),
            general =
            General(
                temp=22.86,
                pressure = 1023,
                humidity = 11,
            ), wind = Wind(
                speed = 20.0,
            ), nameCity = "Iztapalapa"
        )
}
