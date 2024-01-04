package com.test.weatheapp.remote.repository

import com.test.weatheapp.remote.fakes.FakeRemoteData
import com.test.weatheapp.remote.utils.RemoteBaseTest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.test.weatheapp.data.retrofit.WeatherService
import com.test.weatheapp.data.retrofit.mapper.WeatherRetrofitMapper
import com.test.weatheapp.data.retrofit.repository.WeatherRetrofitImp
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
import org.mockito.Mockito.times
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherRemoteImpTest : RemoteBaseTest() {

    @Mock
    lateinit var weatherService: WeatherService

    @Mock
    lateinit var mapper: WeatherRetrofitMapper

    private lateinit var sut: WeatherRetrofitImp

    @Before
    fun setUp() {
        sut = WeatherRetrofitImp(weatherService, mapper)
    }

    @Test
    fun `get weather should return response from remote server`() =
        dispatcher.runTest {
            // Arrange (Given)
            val response = FakeRemoteData.getResponse(7)
            `when`(mapper.mapFromModel(weatherService.getWhether(0.0,0.0,"73cf3fb41f9fb30c702f3471c53bfa37"))) doReturn response.first()

            // Act (When)
            val weathers = sut.getWheather(0.0,0.0,"73cf3fb41f9fb30c702f3471c53bfa37")

            // Assert (Then)
            assertEquals(weathers.weatherCityEntity.size, 1)
            verify(mapper, times(7)).mapFromModel(any())
        }

    @Test
    fun `get weather should return response with empty weather list from remote server`() =
        dispatcher.runTest {
            // Arrange (Given)
            val response = FakeRemoteData.getResponse(0)
            `when`(mapper.mapFromModel(weatherService.getWhether(0.0,0.0,"73cf3fb41f9fb30c702f3471c53bfa37"))) doReturn response.first()

            // Act (When)
            val weathers = sut.getWheather(0.0,0.0,"73cf3fb41f9fb30c702f3471c53bfa37")

            // Assert (Then)
            assertEquals(weathers.weatherCityEntity.size, 0)
            verify(mapper, times(0)).mapFromModel(any())
        }

    @Test
    fun `get weathers should return error from remote server`() =
        dispatcher.runTest {
            // Arrange (Given)
            whenever(weatherService.getWhether(0.0,0.0,"73cf3fb41f9fb30c702f3471c53bfa37")) doAnswer { throw IOException() }

            // Act (When)
            try{
                sut.getWheather(0.0,0.0,"73cf3fb41f9fb30c702f3471c53bfa37")

            }
            catch(exception:IOException){
                // Assert (Then)
                assertThat(
                    exception, instanceOf(IOException::class.java)
                )
            }



            verify(weatherService, times(1)).getWhether(0.0,0.0,"73cf3fb41f9fb30c702f3471c53bfa37")
        }

    @Test
    fun `get character should return error response from remote server`() =
        dispatcher.runTest {
            // Arrange (Given)
            val characterId = 0.0
            whenever(weatherService.getWhether(characterId,0.0,"73cf3fb41f9fb30c702f3471c53bfa37")) doAnswer { throw IOException() }

            // Act (When)
            try{
                sut.getWheather(characterId,0.0,"73cf3fb41f9fb30c702f3471c53bfa37")
            }catch (exception:IOException){
                // Assert (Then)
                assertThat(
                    exception, instanceOf(IOException::class.java)
                )
            }

            verify(weatherService, times(1)).getWhether(characterId,0.0,"73cf3fb41f9fb30c702f3471c53bfa37")
        }
}
