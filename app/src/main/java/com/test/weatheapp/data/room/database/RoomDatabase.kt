package com.test.weatheapp.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.test.weatheapp.data.retrofit.model.WeatherCityClass
import com.test.weatheapp.data.room.converters.Converters
import com.test.weatheapp.data.room.dao.WeatherDao
import com.test.weatheapp.data.room.model.CoordRoom
import com.test.weatheapp.data.room.model.GeneralRoom
import com.test.weatheapp.data.room.model.WeatherRoomClass
import com.test.weatheapp.data.room.model.WindRoom
import com.test.weatheapp.data.room.utils.RoomConstants


@Database(entities = [WeatherRoomClass::class, CoordRoom::class, GeneralRoom::class, WindRoom::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class WeatherDatabase: RoomDatabase() {

    abstract fun cachedWeatherDao(): WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: WeatherDatabase? = null

        fun getInstance(context: Context): WeatherDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            WeatherDatabase::class.java,
            RoomConstants.DB_NAME
        ).addTypeConverter(Converters()).fallbackToDestructiveMigration().build()
    }

    suspend fun populateDatabase() {
        // Llena la tabla con datos por defecto
        cachedWeatherDao().insertAll(getDefaultData())
    }

    private fun getDefaultData(): List<WeatherRoomClass> {
        // Aquí puedes definir tus datos por defecto
        return listOf(
            WeatherRoomClass(
                cord= CoordRoom(longitude =  19.344123, latitude =  -99.061062),
                weatherCityClass = listOf(WeatherCityClass(id= 1, main= "Sun", description = "Full sun", icon = "e10")),
                generalClass =
                GeneralRoom(
                    temp=22.86,
                    feelsLike = 0.0,
                    tempMin = 20.00,
                    tempMax = 24.00,
                    pressure = 1023,
                    humidity = 11,
                    seaLevel = 0,
                    grndLevel = 0
                ), wind = WindRoom(
                    speed = 0.0,
                    deg= 0,
                    gust= 0.0
                ), nameCity = "Iztapalapa"
            ),
            WeatherRoomClass(
                cord= CoordRoom(longitude = 19.344123, latitude =  -99.061062),
                weatherCityClass = listOf(WeatherCityClass(id= 1, main= "Sun", description = "Full sun", icon = "e10")),
                generalClass =
                GeneralRoom(
                    temp=22.86,
                    feelsLike = 0.0,
                    tempMin = 20.00,
                    tempMax = 24.00,
                    pressure = 1023,
                    humidity = 11,
                    seaLevel = 0,
                    grndLevel = 0
                ), wind = WindRoom(
                    speed = 0.0,
                    deg= 0,
                    gust= 0.0
                ), nameCity = "Iztapalapa"
            ),
            WeatherRoomClass(
                cord= CoordRoom(longitude =  10.464973, latitude =  -66.886953),
                weatherCityClass = listOf(WeatherCityClass(id= 1, main= "Rain", description = "Medium rain", icon = "e20")),
                generalClass =
                GeneralRoom(
                    temp=23.67,
                    feelsLike = 0.0,
                    tempMin = 20.00,
                    tempMax = 27.00,
                    pressure = 1013,
                    humidity = 70,
                    seaLevel = 0,
                    grndLevel = 0
                ), wind = WindRoom(
                    speed = 1.03,
                    deg= 110,
                    gust= 0.0
                ), nameCity = "Caracas"
            ),
            WeatherRoomClass(
                cord= CoordRoom(longitude =  3.406703, latitude =  -76.519505),
                weatherCityClass = listOf(WeatherCityClass(id= 1, main= "Rain", description = "Hard rain", icon = "e20")),
                generalClass =
                GeneralRoom(
                    temp=27.80,
                    feelsLike = 0.0,
                    tempMin = 18.00,
                    tempMax = 32.00,
                    pressure = 1008,
                    humidity = 66,
                    seaLevel = 0,
                    grndLevel = 0
                ), wind = WindRoom(
                    speed = 1.72,
                    deg= 323,
                    gust= 0.0
                ), nameCity = "Cali"
            ),
            WeatherRoomClass(
                cord= CoordRoom(longitude = -0.740956, latitude =  -90.318303),
                weatherCityClass = listOf(WeatherCityClass(id= 1, main= "Sun", description = "sun", icon = "e21")),
                generalClass =
                GeneralRoom(
                    temp=26.42,
                    feelsLike = 0.0,
                    tempMin = 18.00,
                    tempMax = 30.00,
                    pressure = 1010,
                    humidity = 90,
                    seaLevel = 0,
                    grndLevel = 0
                ), wind = WindRoom(
                    speed = 4.02,
                    deg= 199,
                    gust= 0.0
                ), nameCity = "Puerto Ayora"
            ),
            WeatherRoomClass(
                cord= CoordRoom(longitude = -3.081459, latitude =  -60.020545),
                weatherCityClass = listOf(WeatherCityClass(id= 1, main= "Sun", description = "sun", icon = "e22")),
                generalClass =
                GeneralRoom(
                    temp=32.27,
                    feelsLike = 0.0,
                    tempMin = 28.00,
                    tempMax = 37.00,
                    pressure = 1008,
                    humidity = 58,
                    seaLevel = 0,
                    grndLevel = 0
                ), wind = WindRoom(
                    speed = 3.09,
                    deg= 30,
                    gust= 0.0
                ), nameCity = "Manaus"
            )

            // Agrega más datos según sea necesario
        )
    }
}
