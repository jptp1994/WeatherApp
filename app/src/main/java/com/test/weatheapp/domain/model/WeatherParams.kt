package com.test.weatheapp.domain.model

import android.content.Context

data class WeatherParams(
    val nameCity: String,
    val context: Context,
    val apiKey:String
)
