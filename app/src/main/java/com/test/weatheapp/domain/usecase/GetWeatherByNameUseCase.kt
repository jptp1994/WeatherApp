package com.test.weatheapp.domain.usecase

import com.test.weatheapp.domain.model.Weather
import com.test.weatheapp.domain.model.WeatherParams
import com.test.weatheapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetWeatherByNameBaseUseCase = BaseUseCase<WeatherParams, Flow<Weather>>

class GetWeatherByNameUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) : GetWeatherByNameBaseUseCase {

    override suspend operator fun invoke(params: WeatherParams) = weatherRepository.getWeather(params)
}
