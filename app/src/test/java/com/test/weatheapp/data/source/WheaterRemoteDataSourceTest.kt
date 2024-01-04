package com.test.weatheapp.data.source

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.test.weatheapp.data.fakes.FakeWeathers
import com.test.weatheapp.data.repository.WeatherRetrofit
import com.test.weatheapp.data.room.mapper.WeatherRoomMapper
import com.test.weatheapp.data.utils.DataBaseTest
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
class WheaterRemoteDataSourceTest : DataBaseTest() {


    @Mock
    lateinit var weatherRoomMapper: WeatherRoomMapper

    @Mock
    lateinit var weatherRetrofit: WeatherRetrofit

    private lateinit var sut: WeatherRemoteDataSource

    @Before
    fun setUp() {
        sut = WeatherRemoteDataSource(weatherRetrofit)
    }

    @Test
    fun `get weather should return weather from remote`() =
        dispatcher.runTest {
            // Arrange (Given)
            `when`(weatherRetrofit.getWheather(0.0,0.0, "ddd")) doReturn FakeWeathers.getWeathers().first()

            // Act (When)
            val weathers = sut.getWeather(0.0,0.0,"ddd")

            // Assert (Then)
            assertEquals(weathers, 1)
            verify(weatherRetrofit, times(1)).getWheather(0.0,0.0,"ddd")
        }

    @Test
    fun `get weather should return error`() =
        dispatcher.runTest {
            // Arrange (Given)
            whenever(weatherRetrofit.getWheather(0.0,0.0,"dd")) doAnswer { throw IOException() }

            // Act (When)

            try{
                sut.getWeather(0.0,0.0,"dd")
            }catch (exception:IOException){

                // Assert (Then)
                assertThat(
                    exception, instanceOf(IOException::class.java)
                )
            }

            verify(weatherRetrofit, times(1)).getWheather(0.0,0.0,"dd")
        }

    @Test
    fun `save weather should return error - not supported by remote`() =
        dispatcher.runTest {
            // Arrange (Given) nothing to arrange

            // Act (When)

            try{
                sut.saveSelectedWeather(weatherRoomMapper.mapToCached(FakeWeathers.getWeathers().first()))
            }catch (exception:UnsupportedOperationException){// Assert (Then)
                assertThat(
                    exception,
                    instanceOf(UnsupportedOperationException::class.java)
                )

            }

        }

    @Test
    fun `get all weather should return error - not supported by remote`() =
        dispatcher.runTest {
            // Arrange (Given) nothing to arrange

            // Act (When)

            try{
                sut.getAllWeather()
            }catch (exception:UnsupportedOperationException){

                // Assert (Then)
                assertThat(
                    exception,
                    instanceOf(UnsupportedOperationException::class.java)
                )
            }


        }

    @Test
    fun `get selected weather should return error - not supported by remote`() =
        dispatcher.runTest {
            // Arrange (Given) nothing to arrange

            // Act (When)
            try{
                sut.getSelectedWeather("Caracas")
            }catch (exception:UnsupportedOperationException){
                // Assert (Then)
                assertThat(
                    exception,
                    instanceOf(UnsupportedOperationException::class.java)
                )
            }

        }

    @Test
    fun `set weather should return error - not supported by remote`() =
        dispatcher.runTest {
            // Arrange (Given) nothing to arrange

            // Act (When)
            try{
                sut.updateSelectedWeather(weatherRoomMapper.mapToCached(FakeWeathers.getWeathers().first()))
            }catch (exception:UnsupportedOperationException){

                // Assert (Then)
                assertThat(
                    exception,
                    instanceOf(UnsupportedOperationException::class.java)
                )
            }

        }

}
