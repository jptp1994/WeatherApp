package com.test.weatheapp.domain.interactor

import androidx.test.core.app.ApplicationProvider
import com.test.weatheapp.domain.fakes.FakeData
import com.test.weatheapp.domain.utils.DomainBaseTest
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.test.weatheapp.domain.model.WeatherParams
import com.test.weatheapp.domain.repository.WeatherRepository
import com.test.weatheapp.domain.usecase.GetWeatherByNameUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetWeatherByNameTestDomain : DomainBaseTest() {

    @Mock
    lateinit var weatherRepository: WeatherRepository

    lateinit var sut: GetWeatherByNameUseCase

    @Before
    fun setUp() {
        sut = GetWeatherByNameUseCase(weatherRepository)
    }

    @Test
    fun `get weather with city name should return success result with weather detail`() =
        dispatcher.runTest {
            // Arrange (Given)
            val cityName = "Iztapalapa"
            whenever(weatherRepository.getWeather(WeatherParams(cityName,
                ApplicationProvider.getApplicationContext(),"dd"))) doReturn FakeData.getWeather()

            // Act (When)
            val weather = sut(WeatherParams(cityName,
                ApplicationProvider.getApplicationContext(),"dd")).single()

            // Assert (Then)
            assertEquals(weather.nameCity, cityName)
            assertEquals(weather.general.temp, 22.86)
            verify(weatherRepository, times(1)).getWeather(WeatherParams(cityName,
                ApplicationProvider.getApplicationContext(),"dd"))
        }

    @Test
    fun `get weather with city name should return error result with exception`() =
        dispatcher.runTest {
            // Arrange (Given)
            val cityName= "Iztapalapa"
            whenever(weatherRepository.getWeather(WeatherParams(cityName,
                ApplicationProvider.getApplicationContext(),"dd"))) doAnswer { throw IOException() }

            // Act (When)
            try{
                sut(WeatherParams(cityName,
                    ApplicationProvider.getApplicationContext(),"dd")).single()
            }catch (exception:IOException){
                // Assert (Then)
                MatcherAssert.assertThat(
                    exception,
                    instanceOf(IOException::class.java)
                )
            }


            verify(weatherRepository, times(1)).getWeather(WeatherParams(cityName,
                ApplicationProvider.getApplicationContext(),"dd"))
        }
}
