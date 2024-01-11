package com.test.weatheapp.data

import com.test.weatheapp.data.mapper.WeatherEntityMapper
import com.test.weatheapp.data.room.mapper.WeatherRoomMapper
import com.test.weatheapp.data.source.WeatherDataSourceFactory
import com.test.weatheapp.domain.model.Weather
import com.test.weatheapp.domain.model.WeatherParams
import com.test.weatheapp.domain.repository.WeatherRepository
import com.test.weatheapp.presentation.utils.NetworkFunctions
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


//Repository for weather
class WeatherRepositoryImp @Inject constructor(
    private val dataSourceFactory: WeatherDataSourceFactory,
    private val weatherMapper: WeatherEntityMapper,
    private val weatherRoomMapper: WeatherRoomMapper,
) : WeatherRepository{

    //Get all weathers from room
    override suspend fun getWeathers():Flow<List<Weather>> = flow {
        val weatherList =
            dataSourceFactory.getCacheDataSource().getAllWeather().map { weatherEntity ->
                weatherMapper.mapFromModel(weatherEntity)
            }
        emit(weatherList)
    }

    /**
     * Get the weather from a specific name put in the searchbar
     * if theres net: search in retrofit for get the information and later filter by the latitude and longitude
     * Later validate if this city is inside of room if is positive update the information else create a new register
     * in case that is not internet try to find inside of room the selected city
     * */
    override suspend fun getWeather(weatherParams:WeatherParams):Flow<Weather> = flow {
        try {
            val isInternet = NetworkFunctions.isNetworkAvailable(weatherParams.context)
            if (isInternet) {
                val geoCoding = dataSourceFactory.getRemoteDataSource()
                    .getCityName(weatherParams.nameCity, weatherParams.apiKey)
                val weatherEntity = dataSourceFactory.getRemoteDataSource().getWeather(
                    geoCoding.first().lat,
                    geoCoding.first().lon, weatherParams.apiKey
                )
                weatherEntity.nameCity= weatherParams.nameCity
                // Obtener datos locales de Room
                val localData = dataSourceFactory.getCacheDataSource()
                    .getSelectedWeather(weatherParams.nameCity)
                // Comparar y actualizar/insetar en la base de datos local
                if (localData.nameCity!="") {
                    // Si el registro ya existe localmente, actualizarlo
                    dataSourceFactory.getCacheDataSource()
                        .updateSelectedWeather(weatherRoomMapper.mapToCached(weatherEntity))
                } else {
                    // Si el registro no existe localmente, insertarlo
                    dataSourceFactory.getCacheDataSource()
                        .saveSelectedWeather(weatherRoomMapper.mapToCached(weatherEntity))
                }
                emit(weatherMapper.mapFromModel(weatherEntity))
            } else {
                emit(
                    weatherMapper.mapFromModel(
                        dataSourceFactory.getCacheDataSource()
                            .getSelectedWeather(weatherParams.nameCity)
                    )
                )
            }
        }catch (e: Exception) {
        // Catch any exception thrown within the coroutine
        throw e
        }
    }
}