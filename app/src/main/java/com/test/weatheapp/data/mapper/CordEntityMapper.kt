package com.test.weatheapp.data.mapper

import com.test.weatheapp.data.model.generalModels.CordEntity
import com.test.weatheapp.data.room.mapper.CacheMapper
import com.test.weatheapp.data.room.model.CoordRoom
import com.test.weatheapp.domain.model.Cord
import javax.inject.Inject

class CordEntityMapper @Inject constructor() :
    EntityMapper<CordEntity, Cord> {

    override fun mapFromModel(model: CordEntity): Cord {
        return Cord(
            latitude = model.latitude,
            longitude = model.longitude,
        )
    }
}
