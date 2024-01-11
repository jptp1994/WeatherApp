package com.test.weatheapp.data.retrofit.mapper


//Base Class for use between retrofit from entity class
interface EntityMapper<M, E> {

    fun mapFromModel(model: M): E
}
