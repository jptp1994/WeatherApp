package com.test.weatheapp.domain.usecase

import com.test.weatheapp.domain.model.Weather
import com.test.weatheapp.domain.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

typealias GetWeatherByListBaseUseCase = BaseUseCase<Unit, Flow<List<Weather>>>


class GetWeatherListUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) : GetWeatherByListBaseUseCase {

    override suspend operator fun invoke(params: Unit) = weatherRepository.getWeathers()
}
