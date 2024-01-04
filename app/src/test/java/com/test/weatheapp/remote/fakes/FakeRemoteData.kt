package com.test.weatheapp.remote.fakes

import com.test.weatheapp.data.model.WeatherEntity
import com.test.weatheapp.data.model.generalModels.CordEntity
import com.test.weatheapp.data.model.generalModels.GeneralEntity
import com.test.weatheapp.data.model.generalModels.WeatherCityEntity
import com.test.weatheapp.data.model.generalModels.WindEntity
import com.test.weatheapp.remote.fakes.FakeValueFactory.randomDouble
import com.test.weatheapp.remote.fakes.FakeValueFactory.randomInt
import com.test.weatheapp.remote.fakes.FakeValueFactory.randomString
import com.test.weatheapp.room.fakes.FakeValueFactory

object FakeRemoteData {

    fun getResponse(size: Int, isRandomId: Boolean = true): List<WeatherEntity> {
        return getFakeWeatherModel(size, isRandomId)
    }

    private fun getFakeWeatherModel(size: Int, isRandomId: Boolean): List<WeatherEntity> {
        val weathers = mutableListOf<WeatherEntity>()
        repeat(size) {
            weathers.add(getWeatherModel(isRandomId))
        }
        return weathers
    }

    fun getWeatherModel(isRandomId: Boolean): WeatherEntity {
        return WeatherEntity(
                 cordEntity = CordEntity(randomDouble(), randomDouble()),
                weatherCityEntity = listOf(WeatherCityEntity(description = randomString(), icon = randomString())),
                generalEntity =
                GeneralEntity(
                    temp= randomDouble(),
                    pressure = randomInt(),
                    humidity = randomInt(),
                ), windEntity = WindEntity(
                    speed = 0.0,
                ), nameCity =  if (isRandomId) randomString() else "Iztapalapa"
            )
    }

    fun getWeatherEntity(isRandomId: Boolean): WeatherEntity {
        return WeatherEntity(
            cordEntity = CordEntity(FakeValueFactory.randomDouble(), FakeValueFactory.randomDouble()),
            weatherCityEntity= listOf(
                WeatherCityEntity(
                FakeValueFactory.randomString(), FakeValueFactory.randomString()
            )
            ),
            generalEntity= GeneralEntity(FakeValueFactory.randomDouble(), FakeValueFactory.randomInt(), FakeValueFactory.randomInt()),
            windEntity= WindEntity(FakeValueFactory.randomDouble()),
            nameCity= if (isRandomId) randomString() else "Iztapalapa",
        )
    }
}
