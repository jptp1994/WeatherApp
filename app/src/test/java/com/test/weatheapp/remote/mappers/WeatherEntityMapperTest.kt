package com.test.weatheapp.remote.mappers

import com.test.weatheapp.remote.fakes.FakeRemoteData
import com.test.weatheapp.remote.utils.RemoteBaseTest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.verify
import com.test.weatheapp.data.mapper.CordEntityMapper
import com.test.weatheapp.data.mapper.GeneralEntityMapper
import com.test.weatheapp.data.mapper.WeatherCityEntityMapper
import com.test.weatheapp.data.mapper.WeatherEntityMapper
import com.test.weatheapp.data.mapper.WindEntityMapper
import com.test.weatheapp.data.model.WeatherEntity
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
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

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherEntityMapperTest : RemoteBaseTest() {

    @Mock
    lateinit var cordEntityMapper: CordEntityMapper

    @Mock
    lateinit var generalEntityMapper: GeneralEntityMapper

    @Mock
    lateinit var weatherCityEntityMapper:WeatherCityEntityMapper

    @Mock
    lateinit var windEntityMapper:WindEntityMapper


    lateinit var sut: WeatherEntityMapper

    @Before
    fun setUp() {
        sut = WeatherEntityMapper(cordEntityMapper, generalEntityMapper, weatherCityEntityMapper, windEntityMapper)
    }

    @Test
    fun `map entity to model should return converted object`() =
        dispatcher.runTest {
            // Arrange (Given)
            val weatherModel = FakeRemoteData.getWeatherModel(false)
            `when`(windEntityMapper.mapFromEntity(weatherModel.windEntity)) doReturn  windEntityMapper.mapFromEntity(FakeRemoteData.getWeatherEntity(
                false).windEntity).copy()


            // Assert (Then)
            assertThat(FakeRemoteData.getWeatherEntity(false), instanceOf(WeatherEntity::class.java))
            assertEquals(FakeRemoteData.getWeatherEntity(false).nameCity, "Iztapalapa")
            assertTrue(weatherModel.nameCity.isNotEmpty())
            verify(cordEntityMapper).mapFromModel(any())
        }
}
