package com.test.weatheapp.room.fakes

import java.util.UUID
import kotlin.random.Random

object FakeValueFactory {

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

    fun randomLong(): Long {
        return Random.nextLong()
    }

    fun randomInt(): Int{
        return Random.nextInt()
    }
    fun randomDouble():Double{
        return Random.nextDouble()
    }

    fun randomBoolean(): Boolean {
        return Random.nextBoolean()
    }
}
