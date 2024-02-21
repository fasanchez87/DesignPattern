package com.me.patterns.creational.prototype

import junit.framework.TestCase.assertEquals
import org.junit.Test

enum class ShapeType {
    RECTANGLE,
    SQUARE,
    CIRCLE
}

abstract class Shape: Cloneable {

    abstract var id: String

    abstract var type: ShapeType

    abstract fun draw()

    public override fun clone(): Any {
        var clone: Any? = null
        try {
            clone = super.clone()
        }
        catch (e: CloneNotSupportedException) {
            e.printStackTrace()
        }
        return clone!!
    }
}

class Rectangle: Shape() {

    override var id: String = "1"

    override var type: ShapeType = ShapeType.RECTANGLE

    override fun draw() {
        println("Inside ${type.name} method.")
    }
}

class Circle: Shape() {

    override var id: String = "2"

    override var type: ShapeType = ShapeType.CIRCLE

    override fun draw() {
        println("Inside ${type.name} method.")
    }
}

class Square: Shape() {

    override var id: String = "3"

    override var type: ShapeType = ShapeType.SQUARE

    override fun draw() {
        println("Inside ${type.name} method.")
    }
}

object ShapeCache {

    private val shapeMap = hashMapOf<String, Shape>()

    init {
        loadCache()
    }

    private fun loadCache() {

        val circle = Circle()
        val rectangle = Rectangle()
        val square = Square()

        shapeMap[circle.id] = circle
        shapeMap[rectangle.id] = rectangle
        shapeMap[square.id] = square
    }

    fun getShape(shapeId: String): Shape {
        val cachedShape = shapeMap[shapeId]
        return cachedShape?.clone() as Shape
    }
}

class Prototipe {

    @Test
    fun testPrototype() {

        val cloneRectangle = ShapeCache.getShape("1")
        assertEquals(cloneRectangle.type, ShapeType.RECTANGLE)
        println("Shape: ${cloneRectangle.type.name}")
        cloneRectangle.draw()

        val cloneCircle = ShapeCache.getShape("2")
        assertEquals(cloneCircle.type, ShapeType.CIRCLE)
        println("Shape: ${cloneCircle.type.name}")
        cloneCircle.draw()

        val cloneSquare = ShapeCache.getShape("3")
        assertEquals(cloneSquare.type, ShapeType.SQUARE)
        println("Shape: ${cloneSquare.type.name}")
        cloneSquare.draw()
    }
}


