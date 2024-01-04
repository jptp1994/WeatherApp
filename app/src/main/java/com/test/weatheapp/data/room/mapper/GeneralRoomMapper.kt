package com.test.weatheapp.data.room.mapper

import com.test.weatheapp.data.model.generalModels.GeneralEntity
import com.test.weatheapp.data.room.model.GeneralRoom
import javax.inject.Inject

class GeneralRoomMapper @Inject constructor() :
    CacheMapper<GeneralRoom, GeneralEntity> {


    override fun mapFromCached(type: GeneralRoom): GeneralEntity {
        return GeneralEntity(
            temp = type.temp,
            pressure = type.pressure,
            humidity= type.humidity
        )
    }

    override fun mapToCached(type: GeneralEntity): GeneralRoom {
        return GeneralRoom(
            temp = type.temp,
            pressure = type.pressure,
            humidity= type.humidity,
            feelsLike = 0.0,
            grndLevel = 0,
            seaLevel = 0,
            tempMax = 0.0,
            tempMin = 0.0
        )
    }
}
