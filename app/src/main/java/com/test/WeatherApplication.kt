package com.test

import android.app.Application
import com.test.weatheapp.data.room.database.WeatherDatabase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@HiltAndroidApp
class WeatherApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        //Use for populate the database
        val database = WeatherDatabase.getInstance(this)
        CoroutineScope(Dispatchers.IO).launch {
            database.populateDatabase()
        }
    }
}