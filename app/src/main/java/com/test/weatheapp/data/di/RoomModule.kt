package com.test.weatheapp.data.di

import android.content.Context
import com.test.weatheapp.data.repository.WeatherRoom
import com.test.weatheapp.data.room.dao.WeatherDao
import com.test.weatheapp.data.room.database.WeatherDatabase
import com.test.weatheapp.data.room.repository.WeatherRoomImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): WeatherDatabase {
        return WeatherDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideWeatherDao(roomDatabase: WeatherDatabase): WeatherDao {
        return roomDatabase.cachedWeatherDao()
    }

    @Provides
    @Singleton
    fun provideWeatherCache(weatherRoomImp: WeatherRoomImp): WeatherRoom {
        return weatherRoomImp
    }

}
