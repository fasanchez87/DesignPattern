package com.me.patterns.behavior.strategy

import junit.framework.TestCase.assertEquals
import org.junit.Test
import java.util.Locale


//Straqtegy Pattern.

class Printer(
    private val strategy: (String) -> String
) {
    fun print(message: String) = strategy(message)
}

//This is strategy; it can be replaced with another strategy.
val lowerCaseStrategy: (String) -> String = {
    it.lowercase(Locale.getDefault())
}

val upperCaseStrategy: (String) -> String = {
    it.uppercase(Locale.getDefault())
}

class Strategy {
    @Test
    fun testStrategy() {

        val input = "HEllo"

        val lowerCasePrinter = Printer(lowerCaseStrategy)
        val outputLower = lowerCasePrinter.print(input)

        val upperCasePrinter = Printer(upperCaseStrategy)
        val outputUpper = upperCasePrinter.print(input)

        assertEquals("hello", outputLower)
        assertEquals("HELLO", outputUpper)
    }
}



