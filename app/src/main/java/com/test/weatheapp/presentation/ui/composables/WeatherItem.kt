package com.test.weatheapp.presentation.ui.composables

import android.os.Bundle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.test.weatheapp.domain.model.Weather


@Composable
fun WeatherItem(weather: Weather) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = weather.nameCity,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Temperature: ${weather.general.temp}°C",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Humidity: ${weather.general.humidity}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Pressure: ${weather.general.pressure}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Wind Speed: ${weather.wind.speed}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Description of Weather: ${weather.weatherCity.first().description}",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 16.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            MapView(weather.cord.latitude,weather.cord.longitude)
        }
    }
}
@Composable
fun MapView(lat: Double, lng: Double) {
    AndroidView(
        factory = { context ->
            MapView(context).apply {
                // Initialize the map view settings
                onCreate(Bundle())
                getMapAsync { googleMap ->
                    // Customize the map as needed
                    val location = LatLng(lat, lng)
                    googleMap.addMarker(MarkerOptions().position(location).title("Marker"))
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10f))
                }
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}