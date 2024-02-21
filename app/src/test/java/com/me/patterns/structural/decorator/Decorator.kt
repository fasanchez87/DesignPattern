package com.me.patterns.structural.decorator

import org.junit.Test

//Pattern: Decorator is same a wrapper, it is used to add new functionality to an existing object without altering its structure.
//This type of design pattern comes under structural pattern as this pattern acts as a wrapper to existing class.

interface MachineCoffee {

    fun makeSmallCoffee(): String

    fun makeLargeCoffee(): String
}

class NormalCoffeeMachine: MachineCoffee {

    override fun makeSmallCoffee() = "Normal Coffee Machine: Making small coffee"

    override fun makeLargeCoffee() = "Normal Coffee Machine: Making large coffee"
}

//Decorator here
class EnhancedCoffeeMachine(
    private val coffeeMachine: MachineCoffee
    //**by keyword is used to delegate the calls to the *original object
): MachineCoffee by coffeeMachine {

    fun makeCoffeeWithMilk() = "Enhanced Coffee Machine: Making coffee with milk"

    fun makeCoffeeWithSugar() = "Enhanced Coffee Machine: Making coffee with sugar"
}

class DecoratorTest {

    @Test
    fun testDecorator(){

        val normalCoffeeMachine = NormalCoffeeMachine()

        val enhancedCoffeeMachine = EnhancedCoffeeMachine(normalCoffeeMachine)

        println(enhancedCoffeeMachine.makeSmallCoffee())
        println(enhancedCoffeeMachine.makeLargeCoffee())

        println(enhancedCoffeeMachine.makeCoffeeWithMilk())
        println(enhancedCoffeeMachine.makeCoffeeWithSugar())

    }
}

