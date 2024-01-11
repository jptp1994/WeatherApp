package com.test.weatheapp.data.mapper

import com.test.weatheapp.data.model.generalModels.WindEntity
import com.test.weatheapp.domain.model.Wind
import javax.inject.Inject

//Mapper from Wind Entity to Wind Model
class WindEntityMapper @Inject constructor() :
    Mapper<WindEntity, Wind> {


    override fun mapFromEntity(type: WindEntity): Wind {
        return Wind(
            speed = type.speed,
        )
    }
    override fun mapToEntity(type: Wind): WindEntity {
        return WindEntity(
            speed = type.speed,
        )
    }
}
