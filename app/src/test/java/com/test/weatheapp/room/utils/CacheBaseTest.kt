package com.test.weatheapp.room.utils

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.test.weatheapp.data.room.dao.WeatherDao
import com.test.weatheapp.data.room.database.WeatherDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import java.io.IOException

@ExperimentalCoroutinesApi
abstract class CacheBaseTest {

    /**
     * A test rule to allow testing coroutines that use the main dispatcher
     */
    @get:Rule
    val testRule = CoroutineTestRule()

    val testScope = testRule.testScope
    private lateinit var database: WeatherDatabase
    protected lateinit var charaterDao: WeatherDao
    private lateinit var context: Context

    @Before
    open fun setup() {
        context = ApplicationProvider.getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, WeatherDatabase::class.java)
            .allowMainThreadQueries().build()
        charaterDao = database.cachedWeatherDao()
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        testScope.runTest {
            database.clearAllTables()
        }
        database.close()
    }
}
