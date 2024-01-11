package com.test.weatheapp.presentation.ui.main

import SearchBar
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.test.weatheapp.presentation.ui.composables.ErrorView
import com.test.weatheapp.presentation.ui.composables.LoadingView
import com.test.weatheapp.presentation.ui.composables.WeatherItem
import com.test.weatheapp.presentation.ui.main.viewmodel.WeatherUIModel
import com.test.weatheapp.presentation.ui.main.viewmodel.WeatherViewModel
import com.test.weatheapp.ui.theme.WeatheAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatheAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    WeatherPrincipal(context = context)
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        WeatheAppTheme {
            val context = LocalContext.current
            WeatherPrincipal(context = context)
        }
    }

    @Composable
    fun WeatherPrincipal(viewModel: WeatherViewModel =hiltViewModel(), context: Context) {
        val weatherUIModel by viewModel.getWeather().observeAsState()
        Column {
            SearchBar{
                viewModel.getWeatherByName(it,context)
            }
            WeatherContent(weatherUIModel = weatherUIModel) {
                viewModel.getWeathers()
            }

        }
    }

    @Composable
    fun WeatherContent(weatherUIModel: WeatherUIModel?, onRetry: () -> Unit) {
        WeatherScreen(weatherUIModel = weatherUIModel, onRetry = onRetry)
    }

    @Composable
    fun WeatherScreen(weatherUIModel: WeatherUIModel?, onRetry: () -> Unit) {
        when (weatherUIModel) {
            is WeatherUIModel.Loading -> {
                LoadingView(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Gray)
                )
            }

            is WeatherUIModel.Error -> {
                ErrorView(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red), error = weatherUIModel.error, onRetry = onRetry
                )
            }

            is WeatherUIModel.Success -> {
                LazyColumn(Modifier.fillMaxSize()) {
                    items(weatherUIModel.data) { weather ->
                        WeatherItem(weather)
                    }
                }
            }

            is WeatherUIModel.SuccessSingle -> {
                WeatherItem(weatherUIModel.data)
            }

            else -> {}
        }
    }
}



