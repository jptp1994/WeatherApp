package com.test.weatheapp.data.retrofit.mapper

import com.test.weatheapp.data.model.generalModels.CordEntity
import com.test.weatheapp.data.retrofit.model.CordRetrofit
import javax.inject.Inject


//Mapper from Cord Retrofit to Cord Entity
class CordRetrofitMapper @Inject constructor() :
    EntityMapper<CordRetrofit, CordEntity> {

    override fun mapFromModel(model: CordRetrofit): CordEntity {
        return CordEntity(
            longitude = model.lon,
            latitude= model.lat
        )
    }
}
