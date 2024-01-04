package com.test.weatheapp.domain.interactor

import com.test.weatheapp.domain.fakes.FakeData
import com.test.weatheapp.domain.utils.DomainBaseTest
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.test.weatheapp.domain.repository.WeatherRepository
import com.test.weatheapp.domain.usecase.GetWeatherListUseCase
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetWeatherListTestDomain : DomainBaseTest() {

    @Mock
    lateinit var weatherRepository: WeatherRepository

    lateinit var sut: GetWeatherListUseCase

    @Before
    fun setUp() {
        sut = GetWeatherListUseCase(weatherRepository)
    }

    @Test
    fun `get weather should return success result with character list`() =
        dispatcher.runTest {
            // Arrange (Given)
            whenever(weatherRepository.getWeathers()) doReturn FakeData.getWeathers()

            // Act (When)
            val weather = sut(Unit).single()

            // Assert (Then)
            assertEquals(weather.size, 2)
            verify(weatherRepository, times(1)).getWeathers()
        }

    @Test
    fun `get weather should return error result with exception`() = dispatcher.runTest {
        // Arrange (Given)
        whenever(weatherRepository.getWeathers()) doAnswer { throw IOException() }

        // Act (When)

        try{
            sut(Unit).single()
        }catch (exception:IOException){
            // Assert (Then)
            assertThat(exception, instanceOf(IOException::class.java))
        }

        verify(weatherRepository, times(1)).getWeathers()
    }
}
