package com.me.patterns.structural.bridge

import org.junit.Assert.assertThrows
import org.junit.Test

class VolumeException(message: String): Exception()

abstract class Device {
    abstract var volume: Int
    abstract val name: String
}

class Radio: Device() {
    override var volume = 0
    override val name: String = "Radio"
}

class Television: Device() {
    override var volume = 0
    override val name: String = "Television"
}

interface Remote {
    fun volumeUp(): String
    fun volumeDown(): String
}

class RemoteControl(private val device: Device) : Remote {
    override fun volumeUp() = "Volume of ${device.name} is: ${device.volume++}"
    override fun volumeDown(): String {
        return if(device.volume > 0){
            "Volume of ${device.name} is: ${device.volume--}"
        }
        else {
            throw VolumeException("Exception down volume")
        }
    }
}

class TestDevices {

    @Test
    fun testBridge(){
        val radio = Radio()
        RemoteControl(radio).volumeUp()
        RemoteControl(radio).volumeUp()
        RemoteControl(radio).volumeUp()

        RemoteControl(radio).volumeDown()
        RemoteControl(radio).volumeDown()
        RemoteControl(radio).volumeDown()

        // This should throw VolumeException
        assertThrows(VolumeException::class.java) {
            RemoteControl(radio).volumeDown()
        }
    }
}