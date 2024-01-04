package com.test.weatheapp.data.source

import com.test.weatheapp.data.utils.DataBaseTest
import com.test.weatheapp.data.repository.WeatherRoom
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WeatherDataSourceFactoryTest : DataBaseTest() {

    @Mock
    lateinit var weatherRoom: WeatherRoom

    @Mock
    lateinit var dataSourceCache: WeatherCacheDataSource

    @Mock
    lateinit var dataSourceRemote: WeatherRemoteDataSource

    private lateinit var sut: WeatherDataSourceFactory

    @Before
    fun setUp() {
        sut = WeatherDataSourceFactory(dataSourceRemote, dataSourceCache)
    }

    @Test
    fun `get data store with cache should return weathers from cache data-source`() =
        dispatcher.runTest {
            // Arrange (Given)
            // Act (When)
            val dataSource = sut.getRemoteDataSource()
            // Assert (Then)
            assertThat(dataSource, instanceOf(WeatherCacheDataSource::class.java))
        }

    @Test
    fun `get data store with cache should return weathers from remote data-source`() =
        dispatcher.runTest {
            // Act (When)
            val dataSource = sut.getRemoteDataSource()
            // Assert (Then)
            assertThat(dataSource, instanceOf(WeatherRemoteDataSource::class.java))
        }
}
