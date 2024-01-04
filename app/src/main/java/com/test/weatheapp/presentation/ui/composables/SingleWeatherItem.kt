package com.test.weatheapp.presentation.ui.composables

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.test.weatheapp.domain.model.Weather

@Composable
fun SingleWeatherItem(weather: Weather) {
    Text(text = "Single Weather Item: ${weather.nameCity}")
}