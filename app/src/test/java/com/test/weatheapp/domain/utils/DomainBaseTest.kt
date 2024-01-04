package com.test.weatheapp.domain.utils

import com.test.weatheapp.domain.utils.CoroutineTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class DomainBaseTest {
    /**
     * A test rule to allow testing coroutines that use the main dispatcher
     */
    @ExperimentalCoroutinesApi
    @get:Rule
    val testRule = CoroutineTestRule()

    val dispatcher = testRule.testScope
}
