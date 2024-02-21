package com.me.patterns.structural.proxy

import org.junit.Test

interface Image {
    fun display()
}

class ImageDisk(
    private val fileName: String
) : Image {

    init {
        loadFromDisk()
    }

    override fun display() {
        println("RealImage: Displaying $fileName")
    }

    private fun loadFromDisk() {
        println("RealImage: Loading $fileName")
    }
}

class ProxyImage(
    private val fileName: String
) : Image {

    private var image: ImageDisk? = null

    override fun display() {
        println("ProxyImage: Displaying $fileName")
        if (image == null) {
            image = ImageDisk(fileName)
        }
        image!!.display()
    }
}

class ProxyTest {

    @Test
    fun testProxy(){
        val image = ProxyImage("test.jpg")
        //image will be loaded from disk
        image.display()
        println("--------")
        //image will not be loaded from disk, it will be loaded from proxy-cache
        image.display()
    }
}


