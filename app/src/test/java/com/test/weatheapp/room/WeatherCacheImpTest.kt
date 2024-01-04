package com.test.weatheapp.room

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.test.weatheapp.data.room.mapper.CordRoomMapper
import com.test.weatheapp.data.room.mapper.GeneralRoomMapper
import com.test.weatheapp.data.room.mapper.WeatherCityRoomMapper
import com.test.weatheapp.data.room.mapper.WeatherRoomMapper
import com.test.weatheapp.data.room.mapper.WindRoomMapper
import com.test.weatheapp.data.room.repository.WeatherRoomImp

import com.test.weatheapp.room.fakes.FakeCacheData
import com.test.weatheapp.room.utils.CacheBaseTest
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@ExperimentalCoroutinesApi
@Config(manifest = Config.NONE)
@RunWith(AndroidJUnit4::class)
class WeatherCacheImpTest : CacheBaseTest() {

    private val generalRoomMapper = GeneralRoomMapper()
    private val cordRoomMapper= CordRoomMapper()
    private val windRoomMapper= WindRoomMapper()
    private val weatherCityRoomMapper= WeatherCityRoomMapper()
    private val weatherRoomMapper = WeatherRoomMapper(generalRoomMapper,cordRoomMapper, windRoomMapper, weatherCityRoomMapper)
    private lateinit var sut: WeatherRoomImp

    @Before
    override fun setup() {
        super.setup()
        sut = WeatherRoomImp(charaterDao, weatherRoomMapper)
    }

    @Test
    fun `get weather should return success weather from local room cache`() =
        testScope.runTest {
            // Arrange (Given)
            val weatherEntity = FakeCacheData.getFakeWeatherEntity(7)
            // Saving weathers to database so we can get it when select query executes
            sut.saveSelectedWeather(weatherRoomMapper.mapToCached(weatherEntity.first()))

            // Act (When)
            val weathers = sut.getAllWeather()

            // Assert (Then)
            assertEquals(weathers.size, 7)
        }

    @Test
    fun `get weather should return success weather from local room cache with empty list`() =
        testScope.runTest{
            // Arrange (Given) no arrange

            // Act (When)
            val weathers = sut.getAllWeather()

            // Assert (Then)
            assertTrue(weathers.isEmpty())
        }

    @Test
    fun `get weather with specific name should return success weather from local room cache`() =
        testScope.runTest {
            // Arrange (Given)
            val characterEntity = FakeCacheData.getFakeWeatherEntity(1)
            // Saving weathers to database so we can get it when select query executes
            sut.saveSelectedWeather(weatherRoomMapper.mapToCached(characterEntity.first()))

            // Act (When)
            val character = sut.getSelectedWeather("Caracas")

            // Assert (Then)
            assertNotNull(character)
            assertEquals(character.nameCity, "Caracas")
        }

    @Test
    fun `save weather should return saved weather from local room cache`() =
        testScope.runTest {
            // Arrange (Given)
            val characterEntity = FakeCacheData.getFakeWeatherEntity(7)
            // Act (When)
            sut.saveSelectedWeather(weatherRoomMapper.mapToCached(characterEntity.first()))
            val weathers = sut.getAllWeather()
            // Assert (Then)
            assertEquals(weathers.size, 7)
        }
}
