package com.test.weatheapp.data.source

import com.test.weatheapp.data.repository.WeatherDataSource
import javax.inject.Inject

open class WeatherDataSourceFactory @Inject constructor(
    private val cacheDataSource: WeatherRemoteDataSource,
    private val remoteDataSource: WeatherCacheDataSource
) {

    fun getRemoteDataSource(): WeatherDataSource {
        return remoteDataSource
    }

    fun getCacheDataSource(): WeatherDataSource {
        return cacheDataSource
    }
}
