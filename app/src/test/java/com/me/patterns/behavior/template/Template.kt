package com.me.patterns.behavior.template

import org.junit.Test

//Pattern template implements the skeleton of an algorithm in the superclass,
//and it lets subclasses override specific steps of the algorithm without changing its structure.

abstract class PizzaMaker {

    fun makePizza() {
        prepareDough()
        addIngredients()
        bakePizza()
        slicePizza()
        deliverPizza()
    }

    abstract fun prepareDough()
    abstract fun addIngredients()
    abstract fun bakePizza()

    open fun slicePizza() {
        println("Slicing the pizza")
    }

    open fun deliverPizza() {
        println("Delivering the pizza")
    }

}

class VeggiePizzaMaker : PizzaMaker() {
    override fun prepareDough() {
        println("Preparing whole wheat dough")
    }

    override fun addIngredients() {
        println("Adding veggies")
    }

    override fun bakePizza() {
        println("Baking at 400 for 20 minutes")
    }
}

class MeatPizzaMaker : PizzaMaker() {
    override fun prepareDough() {
        println("Preparing dough")
    }

    override fun addIngredients() {
        println("Adding meat")
    }

    override fun bakePizza() {
        println("Baking at 375 for 25 minutes")
    }
}

class TestTemplate() {

    @Test
    fun testTemplate() {
        val veggiePizzaMaker = VeggiePizzaMaker()
        veggiePizzaMaker.makePizza()

        val meatPizzaMaker = MeatPizzaMaker()
        meatPizzaMaker.makePizza()
    }

}


