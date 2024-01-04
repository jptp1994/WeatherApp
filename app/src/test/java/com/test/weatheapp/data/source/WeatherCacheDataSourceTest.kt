package com.test.weatheapp.data.source

import com.test.weatheapp.data.fakes.FakeWeathers
import com.test.weatheapp.data.utils.DataBaseTest
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.test.weatheapp.data.repository.WeatherRoom
import com.test.weatheapp.data.room.mapper.WeatherRoomMapper
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherCacheDataSourceTest : DataBaseTest() {

    @Mock
    lateinit var weatherRoom: WeatherRoom

    @Mock
    lateinit var weatherRoomMapper: WeatherRoomMapper

    private lateinit var sut: WeatherCacheDataSource

    @Before
    fun setUp() {
        sut = WeatherCacheDataSource(weatherRoom)
    }

    @Test
    fun `get weather should return weather from local cache`() =
        dispatcher.runTest {
            // Arrange (Given)
            `when`(weatherRoom.getAllWeather()) doReturn FakeWeathers.getWeathers()

            // Act (When)
            val weathers = sut.getAllWeather()

            // Assert (Then)
            assertEquals(weathers.size, 2)
            verify(weatherRoom, times(1)).getAllWeather()
        }

    @Test
    fun `get weather should return error`() =
        dispatcher.runTest {
            // Arrange (Given)
            whenever(weatherRoom.getAllWeather()) doAnswer { throw IOException() }

            // Act (When)
            try{
                sut.getAllWeather()
            }catch (exception:IOException){
                // Assert (Then)
                assertThat(
                    exception, instanceOf(IOException::class.java)
                )
            }

            verify(weatherRoom, times(1)).getAllWeather()
        }

    @Test
    fun `get weather with name city should return weather from local cache`() =
        dispatcher.runTest {
            // Arrange (Given)
            val characterId = "Iztapalapa"
            `when`(weatherRoom.getSelectedWeather(characterId)) doReturn FakeWeathers.getWeathers()[0]

            // Act (When)
            val weathers
            = sut.getSelectedWeather(characterId)

            // Assert (Then)
            assertEquals(weathers.generalEntity.temp, 22.86)
            verify(weatherRoom, times(1)).getSelectedWeather(characterId)
        }

    @Test
    fun `get weather with name city should return error`() =
        dispatcher.runTest {
            // Arrange (Given)
            val characterId = "Iztapalapa"
            whenever(weatherRoom.getSelectedWeather(characterId)) doAnswer { throw IOException() }

            // Act (When)
            try{
                sut.getSelectedWeather(characterId)
            }catch (exception:IOException){
                // Assert (Then)
                assertThat(
                    exception, instanceOf(IOException::class.java)
                )
            }

            verify(weatherRoom, times(1)).getSelectedWeather(characterId)
        }

    @Test
    fun `save weather passed weather list should save the first weather into local cache`() =
        dispatcher.runTest {
            // Arrange (Given)
            val weathers = FakeWeathers.getWeathers()
            // Act (When)
            sut.saveSelectedWeather(weatherRoomMapper.mapToCached(weathers.first()))

            // Assert (Then)
            verify(weatherRoom, times(1)).saveSelectedWeather(weatherRoomMapper.mapToCached(weathers.first()))
        }

    @Test
    fun `save weather passed weather list should return error failed to save last time`() =
        dispatcher.runTest {
            // Arrange (Given)
            val weathers = FakeWeathers.getWeathers()
            whenever(weatherRoom.saveSelectedWeather(weatherRoomMapper.mapToCached(weathers.first()))) doAnswer { throw IOException() }

            // Act (When)
            try{
                sut.saveSelectedWeather(weatherRoomMapper.mapToCached(weathers.first()))
            }catch (exception:IOException){

                // Assert (Then)
                assertThat(
                    exception, instanceOf(IOException::class.java)
                )
            }

            verify(weatherRoom, times(1)).saveSelectedWeather(weatherRoomMapper.mapToCached(weathers.first()))
        }
}
