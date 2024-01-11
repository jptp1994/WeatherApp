package com.test.weatheapp.data.di


import com.test.weatheapp.data.WeatherRepositoryImp
import com.test.weatheapp.domain.repository.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


//Contains the dagger dependency from data module
@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherRepositoryImp: WeatherRepositoryImp): WeatherRepository =
        weatherRepositoryImp

}
