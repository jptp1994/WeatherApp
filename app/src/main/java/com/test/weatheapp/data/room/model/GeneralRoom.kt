package com.test.weatheapp.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.weatheapp.data.room.utils.RoomConstants

@Entity(tableName = RoomConstants.GENERAL_TABLE_NAME)
data class GeneralRoom(
    @PrimaryKey(autoGenerate = true)
    val idGeneral: Long?= 0,
    val temp:Double,
    val feelsLike:Double,
    val tempMin:Double,
    val tempMax:Double,
    val pressure: Int,
    val humidity: Int,
    val seaLevel: Int,
    val grndLevel: Int)