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

        val database = WeatherDatabase.getInstance(this)
        // Llama al método para llenar la tabla con datos por defecto
        CoroutineScope(Dispatchers.IO).launch {
            database.populateDatabase()
        }
    }
}