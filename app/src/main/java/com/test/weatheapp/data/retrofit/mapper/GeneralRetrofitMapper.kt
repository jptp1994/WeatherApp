package com.test.weatheapp.data.retrofit.mapper

import com.test.weatheapp.data.model.generalModels.GeneralEntity
import com.test.weatheapp.data.retrofit.model.GeneralClass
import javax.inject.Inject

//Mapper from General Retrofit to General Entity
class GeneralRetrofitMapper @Inject constructor() :
    EntityMapper<GeneralClass, GeneralEntity> {


    override fun mapFromModel(model: GeneralClass): GeneralEntity {
        return GeneralEntity(
            temp = model.temp,
            pressure = model.pressure,
            humidity= model.humidity
        )
    }
}
