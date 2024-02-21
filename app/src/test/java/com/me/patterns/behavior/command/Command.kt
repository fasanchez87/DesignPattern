package com.me.patterns.behavior.command

import org.junit.Test

interface Command {
    fun execute()
}

class OrderAddCommand(
    private val orderId: Int
) : Command {
    override fun execute() {
        println("OrderAddCommand: Adding order with id: $orderId")
    }
}

class OrderPayCommand(
    private val orderId: Int
) : Command {
    override fun execute() {
        println("OrderPayCommand: Paying for order with id: $orderId")
    }
}

class CommandProcessor {
    private val commands = mutableListOf<Command>()

    fun addCommand(command: Command) = apply {
        commands.add(command)
    }

    fun processCommands() = apply {
        commands.forEach {
            it.execute()
        }
        commands.clear()
    }
}

class CommandTest {

    @Test
    fun testCommand() {
        val commandProcessor = CommandProcessor()
            .addCommand(OrderAddCommand(1))
            .addCommand(OrderAddCommand(2))
            .addCommand(OrderPayCommand(3))
            .processCommands()
    }
}
