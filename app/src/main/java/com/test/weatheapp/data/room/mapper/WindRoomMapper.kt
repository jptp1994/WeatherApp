package com.test.weatheapp.data.room.mapper

import com.test.weatheapp.data.model.generalModels.WindEntity
import com.test.weatheapp.data.room.model.WindRoom
import javax.inject.Inject

class WindRoomMapper @Inject constructor() :
    CacheMapper<WindRoom, WindEntity> {


    override fun mapFromCached(type: WindRoom): WindEntity {
        return WindEntity(
            speed = type.speed,
        )
    }

    override fun mapToCached(type: WindEntity): WindRoom {
        return WindRoom(
            speed = type.speed,
            deg= 0,
            gust = 0.0
        )
    }
}
