package com.me.patterns.structural.composite

import junit.framework.TestCase.assertEquals
import org.junit.Test

open class Component(
    val name: String,
    open val price: Int
)

open class Composite(name: String): Component(name = name, price = 0) {

    private val components = mutableListOf<Component>()

    init {
        println("Creating composite: $name")
    }

    //Forever, for a variable override type, the getter() is mandatory to be implemented in the subclass
    //to be able to access the property from the superclass.
    override val price: Int
        get() = components.sumOf {
            it.price?: 0
        }

    fun addComponent(component: Component) = apply {
        components.add(component)
        //Once the component is added,return the instance of the composite
        //to be able to chain the calls and add more components.
    }
}

class Computer: Composite("Computer")
class Processor: Component("Processor", 500)
class Memory: Composite("Memory")
class Rom: Component("Rom", 200)
class Ram: Component("Ram", 300)
class HardDrive: Component("HardDrive", 300)
class CardGraphics: Component("CardGraphics", 300)

class CompositeTest {

    @Test
    fun compositeTest() {

        val memory = Memory()
            .addComponent(Rom())
            .addComponent(Ram())

        val computer = Computer()
            .addComponent(Processor())
            .addComponent(memory)
            .addComponent(HardDrive())
            .addComponent(CardGraphics())

        assertEquals(computer.price, 1400)
        assertEquals(memory.price, 500)
        assertEquals(computer.name, "Computer")
    }
}





