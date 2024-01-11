package com.test.weatheapp.presentation.ui.composables

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


//Map view

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