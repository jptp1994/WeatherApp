package com.test.weatheapp.data.mapper


import com.test.weatheapp.data.fakes.FakeWeathers
import com.test.weatheapp.data.utils.DataBaseTest
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherWindAndGeneralMapperTest : DataBaseTest() {

    private lateinit var sut: GeneralEntityMapper

    private lateinit var sutMap: WindEntityMapper

    @Before
    fun setUp() {
        sutMap= WindEntityMapper()
        sut = GeneralEntityMapper()
    }

    @Test
    fun `map  location entity to location should return converted location`() =
        dispatcher.runTest {
            // Arrange (Given)
            val locationFake = FakeWeathers.getWeathers()[0].generalEntity

            // Act (When)
            val locationMapper = sut.mapFromModel(locationFake)

            // Assert (Then)
            assertEquals(locationMapper.temp, 22.86)
        }

    @Test
    fun `map  location to location entity should return converted location`() =
        dispatcher.runTest {
            // Arrange (Given)
            val locationFake = FakeWeathers.getWeather().wind

            // Act (When)
            val locationMapper = sutMap.mapToEntity(locationFake)

            // Assert (Then)
            assertEquals(locationMapper.speed, 20.0)
        }
}
