package com.test.weatheapp.remote.mappers

import com.test.weatheapp.data.mapper.WindEntityMapper
import com.test.weatheapp.domain.model.Wind
import com.test.weatheapp.remote.fakes.FakeRemoteData
import com.test.weatheapp.remote.utils.RemoteBaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherWindEntityMapperTest : RemoteBaseTest() {

    lateinit var sut: WindEntityMapper

    @Before
    fun setUp() {
        sut = WindEntityMapper()
    }

    @Test
    fun `map model to entity should return converted object`() =
        dispatcher.runTest {
            // Arrange (Given)
            val locationModel = FakeRemoteData.getWeatherModel(false).windEntity

            // Act (When)
            val locationEntity = sut.mapFromEntity(locationModel)

            // Assert (Then)
            assertThat(locationEntity, instanceOf(Wind::class.java))
        }
}
