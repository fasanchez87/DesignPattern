package com.me.patterns.creational.singleton

import org.junit.Assert
import org.junit.Test

object NetworkDriver {

    init {
        println("Initializing: $this")
    }

    fun log(): NetworkDriver = apply {
        println("Logging: $this")
    }

}

class SingletonTest {

    @Test
    fun testSingleton() {
        println("Start")
        val networkDriver1 = NetworkDriver.log()
        val networkDriver2 = NetworkDriver.log()

        Assert.assertEquals(networkDriver1, NetworkDriver)
        Assert.assertEquals(networkDriver2, NetworkDriver)
    }
}