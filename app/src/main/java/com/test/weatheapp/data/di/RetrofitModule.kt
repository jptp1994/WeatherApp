package com.test.weatheapp.data.di


import com.test.weatheapp.data.repository.WeatherRetrofit
import com.test.weatheapp.data.retrofit.ServiceFactory
import com.test.weatheapp.data.retrofit.WeatherService
import com.test.weatheapp.data.retrofit.repository.WeatherRetrofitImp
import com.test.weatheapp.presentation.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideWeatherService(): WeatherService {
        return ServiceFactory.create(true,BASE_URL)
    }

    @Provides
    @Singleton
    fun provideWeatherRemote(weatherRetrofitImp: WeatherRetrofitImp): WeatherRetrofit {
        return weatherRetrofitImp
    }
}
