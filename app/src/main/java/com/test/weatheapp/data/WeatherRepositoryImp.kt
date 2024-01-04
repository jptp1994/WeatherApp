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

class WeatherRepositoryImp @Inject constructor(
    private val dataSourceFactory: WeatherDataSourceFactory,
    private val weatherMapper: WeatherEntityMapper,
    private val weatherRoomMapper: WeatherRoomMapper,
) : WeatherRepository{
    override suspend fun getWeathers():Flow<List<Weather>> = flow {
        val weatherList =
            dataSourceFactory.getCacheDataSource().getAllWeather().map { weatherEntity ->
                weatherMapper.mapFromModel(weatherEntity)
            }
        emit(weatherList)
    }


    override suspend fun getWeather(weatherParams:WeatherParams):Flow<Weather> = flow {
        val isInternet= NetworkFunctions.isNetworkAvailable(weatherParams.context)
        if (isInternet){
            val geoCoding= dataSourceFactory.getRemoteDataSource().getCityName(weatherParams.nameCity,weatherParams.apiKey)
            val weatherEntity= dataSourceFactory.getRemoteDataSource().getWeather(geoCoding.geocodings.first().lat,
                    geoCoding.geocodings.first().lon, weatherParams.apiKey)
                // Obtener datos locales de Room
                val localData = dataSourceFactory.getCacheDataSource().getSelectedWeather(weatherParams.nameCity)
                // Comparar y actualizar/insetar en la base de datos local
                if (localData != null && localData.nameCity == weatherEntity.nameCity) {
                    // Si el registro ya existe localmente, actualizarlo
                    dataSourceFactory.getCacheDataSource()
                        .updateSelectedWeather(weatherRoomMapper.mapToCached(weatherEntity))
                } else {
                    // Si el registro no existe localmente, insertarlo
                    dataSourceFactory.getCacheDataSource().saveSelectedWeather(weatherRoomMapper.mapToCached(weatherEntity))
                }
                emit(weatherMapper.mapFromModel(weatherEntity))



        } else{
            emit(
                weatherMapper.mapFromModel(dataSourceFactory.getCacheDataSource().getSelectedWeather(weatherParams.nameCity))
            )
        }
    }
}