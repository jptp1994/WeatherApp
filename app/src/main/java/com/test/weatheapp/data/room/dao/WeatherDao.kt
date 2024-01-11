package com.test.weatheapp.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.test.weatheapp.data.room.model.WeatherRoomClass

//Contains the methods available for the weather information
@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather where nameCity LIKE '%' || :nameCity || '%' ")
    fun getSelectedWeather(nameCity:String): WeatherRoomClass

    @Query("SELECT * FROM weather")
    fun getAllWeather(): List<WeatherRoomClass>

    @Update
    suspend fun updateSelectedWeather(weather: WeatherRoomClass)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveSelectedWeather(weather: WeatherRoomClass)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<WeatherRoomClass>)
}
