package com.test.weatheapp.data.room.mapper

interface CacheMapper<T, V> {

    fun mapFromCached(type: T): V

    fun mapToCached(type: V): T
}
