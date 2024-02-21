package com.me.patterns.creational.builder

import junit.framework.TestCase.assertEquals
import org.junit.Test


class Component(
    builder: Builder
) {

    val name: String?
    val lastName: String?
    val age: Int?
    val nick: Int?

    init {
        name = builder.name
        lastName = builder.lastName
        age = builder.age
        nick = builder.nick
    }

    data class Builder(
        var name: String? = null,
        var lastName: String? = null,
        var age: Int? = null,
        var nick: Int? = null
    ) {
        fun name(name: String) = apply { this.name = name }
        fun lastName(lastName: String) = apply { this.lastName = lastName }
        fun age(age: Int) = apply { this.age = age }
        fun nick(nick: Int) = apply { this.nick = nick }

        fun build() = Component(this)
    }
}

class ComponentTest {

    @Test
    fun testBuilder() {
        val component = Component.Builder()
            .name("John")
            .lastName("Doe")
            .age(30)
            .build()

        assertEquals(component.name, "John")
        assertEquals(component.lastName, "Doe")
        assertEquals(component.age, 30)
    }
}