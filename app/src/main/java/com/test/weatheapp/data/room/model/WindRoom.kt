package com.test.weatheapp.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.weatheapp.data.room.utils.RoomConstants

@Entity(tableName = RoomConstants.WIND_TABLE_NAME)
data class WindRoom(
    @PrimaryKey(autoGenerate = true)
    val idWind: Long?= 0,
    val speed:Double,
    val deg:Int,
    val gust:Double)