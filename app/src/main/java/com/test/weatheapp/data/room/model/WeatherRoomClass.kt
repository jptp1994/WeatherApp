package com.test.weatheapp.data.room.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomWarnings
import androidx.room.TypeConverters
import com.test.weatheapp.data.retrofit.model.WeatherCityClass
import com.test.weatheapp.data.room.converters.Converters
import com.test.weatheapp.data.room.utils.RoomConstants
@Entity(tableName = RoomConstants.WEATHER_TABLE_NAME)
data class WeatherRoomClass(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    @Embedded
    val cord: CoordRoom,
    @TypeConverters(Converters::class)
    val weatherCityClass: List<WeatherCityClass>,
    @Embedded
    val generalClass: GeneralRoom,
    @Embedded
    val wind:WindRoom,
    val nameCity: String,
    )
