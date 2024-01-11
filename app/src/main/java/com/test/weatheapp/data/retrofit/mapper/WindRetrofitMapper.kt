package com.test.weatheapp.data.retrofit.mapper

import com.test.weatheapp.data.model.generalModels.WindEntity
import com.test.weatheapp.data.retrofit.model.WindClass
import javax.inject.Inject

//Mapper from Wind Retrofit to Wind Entity

class WindRetrofitMapper @Inject constructor() :
    EntityMapper<WindClass, WindEntity> {


    override fun mapFromModel(model: WindClass): WindEntity {
        return WindEntity(
            speed = model.speed,
        )
    }
}
