package com.test.weatheapp.data.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.weatheapp.data.room.utils.RoomConstants

@Entity(tableName = RoomConstants.CORD_TABLE_NAME)
data class CoordRoom(
    @PrimaryKey(autoGenerate = true)
    val idCord: Long?= 0,
    val longitude:Double,
    val latitude:Double)