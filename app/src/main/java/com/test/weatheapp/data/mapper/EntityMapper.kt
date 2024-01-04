package com.test.weatheapp.data.mapper

interface EntityMapper<M, E> {

    fun mapFromModel(model: M): E
}
