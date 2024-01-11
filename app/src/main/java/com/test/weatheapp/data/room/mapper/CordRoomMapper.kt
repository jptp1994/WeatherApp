package com.test.weatheapp.data.room.mapper

import com.test.weatheapp.data.model.generalModels.CordEntity
import com.test.weatheapp.data.room.model.CoordRoom
import javax.inject.Inject

//Mapper from Cord Room to Cord Entity
class CordRoomMapper @Inject constructor() :
    CacheMapper<CoordRoom, CordEntity> {


    override fun mapFromCached(type: CoordRoom): CordEntity {
        return CordEntity(
            latitude = type.latitude,
            longitude = type.longitude,
        )
    }

    override fun mapToCached(type: CordEntity): CoordRoom {
        return CoordRoom(
            latitude = type.latitude,
            longitude = type.longitude,
        )
    }
}
