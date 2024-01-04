package com.test.weatheapp.room.fakes

import com.test.weatheapp.data.model.WeatherEntity
import com.test.weatheapp.data.model.generalModels.CordEntity
import com.test.weatheapp.data.model.generalModels.GeneralEntity
import com.test.weatheapp.data.model.generalModels.WeatherCityEntity
import com.test.weatheapp.data.model.generalModels.WindEntity
import com.test.weatheapp.room.fakes.FakeValueFactory.randomDouble
import com.test.weatheapp.room.fakes.FakeValueFactory.randomInt
import com.test.weatheapp.room.fakes.FakeValueFactory.randomString

object FakeCacheData {

    fun getFakeWeatherEntity(
        size: Int,
    ): List<WeatherEntity> {
        val weathers = mutableListOf<WeatherEntity>()
        repeat(size) {
            weathers.add(createWeatherEntity())
        }
        return weathers
    }

    private fun createWeatherEntity(): WeatherEntity {
        return WeatherEntity(
            cordEntity = CordEntity(randomDouble(), randomDouble()),
            weatherCityEntity= listOf(WeatherCityEntity(
                randomString(), randomString()
            )),
            generalEntity=GeneralEntity(randomDouble(), randomInt(), randomInt()),
            windEntity=WindEntity(randomDouble()),
            nameCity= randomString(),
           )
    }
}
