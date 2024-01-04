package com.test.weatheapp.data.retrofit.mapper

interface EntityMapper<M, E> {

    fun mapFromModel(model: M): E
}
