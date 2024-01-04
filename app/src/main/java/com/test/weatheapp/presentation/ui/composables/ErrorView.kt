package com.test.weatheapp.presentation.ui.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ErrorView(modifier: Modifier, error: String, onRetry: () -> Unit) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = "Error: $error",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(16.dp),
        )
        Button(
            onClick = onRetry,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        ) {
            Text(text = "Retry")
        }
    }
}