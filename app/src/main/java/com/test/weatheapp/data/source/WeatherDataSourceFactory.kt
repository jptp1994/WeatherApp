package com.test.weatheapp.data.source

import com.test.weatheapp.data.repository.WeatherDataSource
import javax.inject.Inject


//Factory for datasource
open class WeatherDataSourceFactory @Inject constructor(
    private val cacheDataSource: WeatherCacheDataSource,
    private val remoteDataSource: WeatherRemoteDataSource
) {

    fun getRemoteDataSource(): WeatherDataSource {
        return remoteDataSource
    }

    fun getCacheDataSource(): WeatherDataSource {
        return cacheDataSource
    }
}
