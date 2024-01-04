package com.test.weatheapp.presentation.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

@Composable
fun MapsScreen(mapView: MapView, latitude: Double, longitude: Double) {

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        MapContainer(
            modifier = Modifier
                .fillMaxSize()
                .height(200.dp)
                .padding(16.dp),
            mapView = mapView,
            latitude= latitude,
            longitude= longitude
        )
    }
}

@Composable
fun MapContainer(
    modifier: Modifier = Modifier,
    mapView: MapView,
    latitude: Double,
    longitude: Double
) {
    AndroidView(
        factory = { mapView },
        modifier = modifier
    ) { map ->
        map.onCreate(null) // Handle this based on your activity lifecycle

        map.getMapAsync { googleMap ->
            // Move the camera to the specified coordinates
            googleMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(latitude, longitude),
                    12f
                )
            )

            // Add a marker at the specified coordinates
            googleMap.addMarker(
                MarkerOptions()
                    .position(LatLng(latitude, longitude))
                    .title("Marker Title")
            )
        }
    }
}