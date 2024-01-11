package com.test.weatheapp.data.mapper


//Base Class for use between entity from model class
interface EntityMapper<M, E> {

    fun mapFromModel(model: M): E
}
