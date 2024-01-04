package com.test.weatheapp.data

import androidx.test.core.app.ApplicationProvider
import com.test.weatheapp.data.fakes.FakeWeathers
import com.test.weatheapp.data.utils.DataBaseTest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.anyOrNull
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.test.weatheapp.data.mapper.WeatherEntityMapper
import com.test.weatheapp.data.model.WeatherEntity
import com.test.weatheapp.data.repository.WeatherDataSource
import com.test.weatheapp.data.room.mapper.WeatherRoomMapper
import com.test.weatheapp.data.source.WeatherDataSourceFactory
import com.test.weatheapp.domain.model.WeatherParams
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.single
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
class WeatherRepositoryImpTest : DataBaseTest() {

    @Mock
    lateinit var dataSourceFactory: WeatherDataSourceFactory

    @Mock
    lateinit var weatherEntityMapper: WeatherEntityMapper

    @Mock
    lateinit var weatherRoomMapper: WeatherRoomMapper

    @Mock
    lateinit var dataSource: WeatherDataSource

    private lateinit var sut: WeatherRepositoryImp

    @Before
    fun setUp() {
        sut = WeatherRepositoryImp(dataSourceFactory, weatherEntityMapper, weatherRoomMapper)
    }

    @Test
    fun `get weather should return weather list from local cache`() =
        dispatcher.runTest {
            // Arrange (Given)
            `when`(dataSourceFactory.getCacheDataSource()) doReturn dataSource
            `when`(dataSourceFactory.getCacheDataSource()) doReturn dataSource
            `when`(
                dataSourceFactory.getCacheDataSource().getAllWeather()
            ) doReturn FakeWeathers.getWeathers()

            // Act (When)
            val weathers = sut.getWeathers().single()

            // Assert (Then)
            assertEquals(weathers.size, 2)
            verify(dataSourceFactory, times(2)).getCacheDataSource()
            verify(dataSourceFactory, times(2)).getCacheDataSource()
            verify(dataSourceFactory.getCacheDataSource(), times(1)).getAllWeather()
            verify(weatherEntityMapper, times(2)).mapFromModel(any())
        }

    @Test
    fun `get weather should return weather list from local cache and saved the weather to local db`() =
        dispatcher.runTest {
            // Arrange (Given)
            val isCached = true
            `when`(dataSourceFactory.getCacheDataSource()) doReturn dataSource
            `when`(dataSourceFactory.getCacheDataSource()) doReturn dataSource
            `when`(
                dataSourceFactory.getCacheDataSource().getAllWeather()
            ) doReturn FakeWeathers.getWeathers()

            // Act (When)
            val weathers = sut.getWeathers().single()

            // Assert (Then)
            assertEquals(weathers.size, 2)
            verify(dataSourceFactory, times(2)).getCacheDataSource()
            verify(dataSourceFactory, times(2)).getCacheDataSource()
            verify(dataSourceFactory.getCacheDataSource(), times(1)).getAllWeather()
            verify(weatherEntityMapper, times(2)).mapFromModel(anyOrNull())
            verify(dataSourceFactory.getCacheDataSource(), times(1)).saveSelectedWeather(any())
        }

    @Test
    fun `get weather should return weather list from remote`() =
        dispatcher.runTest {
            // Arrange (Given)
            `when`(dataSourceFactory.getRemoteDataSource()) doReturn dataSource
            `when`(dataSourceFactory.getRemoteDataSource()) doReturn dataSource
            `when`(
                dataSourceFactory.getRemoteDataSource().getWeather(0.0,0.0,"73cf3fb41f9fb30c702f3471c53bfa37")
            ) doReturn FakeWeathers.getWeathers().first()

            // Assert (Then)
            verify(dataSourceFactory, times(2)).getRemoteDataSource()
            verify(dataSourceFactory, times(2)).getRemoteDataSource()
            verify(dataSourceFactory.getRemoteDataSource(), times(1)).getWeather(0.0,0.0,"73cf3fb41f9fb30c702f3471c53bfa37")
            verify(weatherEntityMapper, times(2)).mapFromModel(any())
        }

    @Test
    fun `get city name should return weather from local cache`() =
        dispatcher.runTest {
            // Arrange (Given)
            val characterId = "Iztapalapa"
            `when`(dataSourceFactory.getCacheDataSource()) doReturn dataSource
            `when`(dataSource.getSelectedWeather(characterId)) doReturn FakeWeathers.getWeathers()[0]
            `when`(weatherEntityMapper.mapFromModel(any())) doReturn FakeWeathers.getWeather()

            // Act (When)
            val weather = sut.getWeather(WeatherParams("Iztapalapa", ApplicationProvider.getApplicationContext(),"73cf3fb41f9fb30c702f3471c53bfa37")).single()

            // Assert (Then)
            assertEquals(weather.nameCity, "Iztapalapa")
            assertEquals(weather.wind.speed, 0.0)
            verify(dataSourceFactory, times(1)).getCacheDataSource()
            verify(dataSourceFactory.getCacheDataSource(), times(1)).getSelectedWeather(characterId)
            verify(weatherEntityMapper, times(1)).mapFromModel(any())
        }

    @Test
    fun `get weather with id should return character from remote`() =
        dispatcher.runTest {
            // Arrange (Given)
            val characterId = "Caracas"
            val mockCharacter = mock<WeatherEntity> { WeatherEntity::class.java }
            `when`(mockCharacter.copy()) doReturn FakeWeathers.getWeathers().first()
            `when`(dataSourceFactory.getCacheDataSource()) doReturn dataSource
            `when`(dataSource.getSelectedWeather(characterId)) doReturn mockCharacter
            `when`(dataSourceFactory.getRemoteDataSource()) doReturn dataSource

            // Act (When)
            sut.getWeather(WeatherParams(characterId,ApplicationProvider.getApplicationContext(),"73cf3fb41f9fb30c702f3471c53bfa37")).single()

            // Assert (Then)
            verify(dataSourceFactory, times(1)).getCacheDataSource()
            verify(dataSourceFactory.getCacheDataSource(), times(2)).getSelectedWeather(characterId)
            verify(dataSourceFactory, times(1)).getRemoteDataSource()
            verify(dataSourceFactory.getRemoteDataSource(), times(2)).getWeather(0.0,0.0,"73cf3fb41f9fb30c702f3471c53bfa37")
            verify(weatherEntityMapper, times(1)).mapFromModel(any())
        }
}
