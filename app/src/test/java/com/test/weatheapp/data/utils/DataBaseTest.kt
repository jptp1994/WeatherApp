package com.test.weatheapp.data.utils

import com.test.weatheapp.data.utils.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class DataBaseTest {
    /**
     * A test rule to allow testing coroutines that use the main dispatcher
     */
    @ExperimentalCoroutinesApi
    @get:Rule
    val testRule = CoroutineTestRule()

    val dispatcher = testRule.testScope

}
