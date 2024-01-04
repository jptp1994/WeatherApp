package com.test.weatheapp.data.mapper

import com.test.weatheapp.data.model.generalModels.GeneralEntity
import com.test.weatheapp.data.retrofit.model.GeneralClass
import com.test.weatheapp.domain.model.General
import javax.inject.Inject

class GeneralEntityMapper @Inject constructor() :
    EntityMapper<GeneralEntity, General> {

    override fun mapFromModel(model: GeneralEntity): General {
        return General(
            temp = model.temp,
            pressure = model.pressure,
            humidity= model.humidity
        )
    }
}
