package com.me.patterns.behavior.mediator

import org.junit.Test

//Mediator Pattern.
//This pattern is used to reduce communication complexity between multiple objects or classes.

class Chat(
    private val mediator: Mediator,
    val name: String?
) {
    fun send(message: String, user: String? = null) {
        mediator.sendMessage(message, user)
    }

    fun receiveMessage(message: String) {
        println("$name received: $message")
    }
}


class Mediator {

    private val users = mutableListOf<Chat>()

    fun sendMessage(
        message: String,
        user: String?
    ) {
       users.filter {
            it.name != user || user == null
         }
       .forEach {
            it.receiveMessage(message)
        }
    }

    fun addUser(user: Chat) = apply {
        users.add(user)
    }
}

class TestChat {

    @Test
    fun testChat(){

        val mediator = Mediator()
        val user1 = Chat(mediator, "User1")
        val user2 = Chat(mediator, "User2")
        val user3 = Chat(mediator, "User3")

        mediator.addUser(user1)
        mediator.addUser(user2)
        mediator.addUser(user3)

        user1.send("Hello", "User2")
        user2.send("Hi")
    }
}
