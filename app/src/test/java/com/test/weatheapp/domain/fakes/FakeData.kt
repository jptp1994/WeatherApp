package com.test.weatheapp.domain.fakes

import com.test.weatheapp.domain.model.Cord
import com.test.weatheapp.domain.model.General
import com.test.weatheapp.domain.model.Weather
import com.test.weatheapp.domain.model.WeatherCity
import com.test.weatheapp.domain.model.Wind
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakeData {
    fun getWeathers(): Flow<List<Weather>> = flow {
        val weathers = listOf(
            Weather(
                cord = Cord(19.344123, -99.061062),
                weatherCity = listOf(WeatherCity(description = "Full sun", icon = "e10")),
                general =
                General(
                    temp=22.86,
                    pressure = 1023,
                    humidity = 11,
                ), wind = Wind(
                    speed = 0.0,
                ), nameCity = "Iztapalapa"
            ),
            Weather(
                cord= Cord(19.344123, -99.061062),
                weatherCity = listOf(WeatherCity(description = "Full sun", icon = "e10")),
                general =
                General(
                    temp=22.86,
                    pressure = 1023,
                    humidity = 11,
                ), wind = Wind(
                    speed = 0.0,
                ), nameCity = "Caracas"
            )
        )
        emit(weathers)
    }

    fun getWeather(): Flow<Weather> = flow {
        emit(
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
        )
    }

}
