package com.me.patterns.behavior.template

import org.junit.Assert.assertEquals
import org.junit.Test

//Memento Pattern.
//This pattern is used to restore state of an object to a previous state.
class Memento(
    val state: String
)

class Originator(
    var state: String
) {
    fun createMemento() = Memento(state)

    fun restore(memento: Memento) {
        state = memento.state
    }
}

class CareTaker {
    private val mementoList = mutableListOf<Memento>()

    fun saveState(memento: Memento) = apply {
        mementoList.add(memento)
    }

    fun restore(index: Int) = mementoList[index]
}

class TestMemento{

    @Test
    fun testMemento(){

        val originator = Originator("initial state")

        val careTaker = CareTaker()
        careTaker.saveState(originator.createMemento())

        originator.state = "State 1"
        careTaker.saveState(originator.createMemento())

        assertEquals(originator.state, "State 1")

        originator.state = "State 2"
        careTaker.saveState(originator.createMemento())

        assertEquals(originator.state, "State 2")

        println("Current State: ${originator.state}")

        originator.restore(careTaker.restore(0))
        println("First saved State: ${originator.state}")
        assertEquals(originator.state, "initial state")

        originator.restore(careTaker.restore(1))
        println("Second saved State: ${originator.state}")
        assertEquals(originator.state, "State 1")
    }
}