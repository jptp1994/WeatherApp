package com.test.weatheapp.presentation.ui.main.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.weatheapp.domain.model.Weather
import com.test.weatheapp.domain.model.WeatherParams
import com.test.weatheapp.domain.usecase.GetWeatherByNameUseCase
import com.test.weatheapp.domain.usecase.GetWeatherListUseCase
import com.test.weatheapp.presentation.base.BaseViewModel
import com.test.weatheapp.presentation.utils.Constants
import com.test.weatheapp.presentation.utils.CoroutineContextProvider
import com.test.weatheapp.presentation.utils.UiAwareLiveData
import com.test.weatheapp.presentation.utils.UiAwareModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

sealed class WeatherUIModel : UiAwareModel() {
    object Loading : WeatherUIModel()
    data class Error(var error: String) : WeatherUIModel()
    data class Success(val data: List<Weather>) : WeatherUIModel()
    data class SuccessSingle(val data: Weather) : WeatherUIModel()

}

@HiltViewModel
class WeatherViewModel @Inject constructor(
    contextProvider: CoroutineContextProvider,
    private val
    getWeatherListUseCase: GetWeatherListUseCase,
    private val getWeatherByNameUseCase: GetWeatherByNameUseCase
) : BaseViewModel(contextProvider) {

    private val _weatherList = UiAwareLiveData<WeatherUIModel>()
    private val weatherList: LiveData<WeatherUIModel> by lazy { _weatherList }

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _weatherList.postValue(WeatherUIModel.Error( exception.toString()))
    }

    init {
        getWeathers()
    }
    fun getWeathers() {
        _weatherList.postValue(WeatherUIModel.Loading)
        launchCoroutineIO {
            loadWeatherList()
        }
    }

    fun getWeatherByName(nameCity:String, context: Context) {
        _weatherList.postValue(WeatherUIModel.Loading)
        launchCoroutineIO {
            loadWeatherByName(nameCity,context)
        }
    }

    fun getWeather(): LiveData<WeatherUIModel> {
        return weatherList
    }


    private suspend fun loadWeatherList() {
        getWeatherListUseCase(Unit).collect {
            _weatherList.postValue(WeatherUIModel.Success(it))
        }
    }

    private suspend fun loadWeatherByName(nameCity: String, context: Context) {
        getWeatherByNameUseCase(WeatherParams(nameCity,context, Constants.API_KEY)).collect {
            _weatherList.postValue(WeatherUIModel.SuccessSingle(it))
        }
    }
}
