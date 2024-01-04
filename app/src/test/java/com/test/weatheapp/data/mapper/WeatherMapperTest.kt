package com.test.weatheapp.data.mapper

import com.test.weatheapp.data.fakes.FakeWeathers
import com.test.weatheapp.data.utils.DataBaseTest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.test.weatheapp.domain.model.Cord
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherMapperTest : DataBaseTest() {

    @Mock
    lateinit var cordEntityMapper: CordEntityMapper

    @Mock
    lateinit var generalEntityMapper: GeneralEntityMapper
    @Mock
    lateinit var weatherCityEntityMapper: WeatherCityEntityMapper
    @Mock
    lateinit var windEntityMapper: WindEntityMapper


    private lateinit var sut: WeatherEntityMapper

    @Before
    fun setUp() {
        sut = WeatherEntityMapper(cordEntityMapper, generalEntityMapper, weatherCityEntityMapper, windEntityMapper)
    }

    @Test
    fun `map weather entity to weather should return converted weather`() =
        dispatcher.runTest {
            // Arrange (Given)
            val characterFake = FakeWeathers.getWeathers()[0]
            `when`(cordEntityMapper.mapFromModel(characterFake.cordEntity)) doReturn Cord(
                0.0,
                0.0
            )
            // Act (When)
            val weather = sut.mapFromModel(characterFake)

            // Assert (Then)
            assertEquals(weather.nameCity, "Iztapalapa")
            verify(cordEntityMapper, times(1)).mapFromModel(any())
        }
}
