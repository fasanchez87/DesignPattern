package com.me.patterns.behavior.observer

import org.junit.Test
import java.io.File

//Observer Pattern.
interface EventListener {
    fun update(
        eventType: String?,
        file: File?
    )
}

class EventManager(
    vararg operations: String
) {
    private var listeners: MutableMap<String, MutableList<EventListener>> = mutableMapOf()

    init {
        operations.forEach {
            listeners[it] = mutableListOf()
        }
    }

    //This is event subscriber.
    fun subscribe(
        eventType: String?,
        listener: EventListener
    ) {
        val users = listeners[eventType]
        users?.add(listener)
    }

    fun unsubscribe(
        eventType: String?,
        listener: EventListener
    ) {
        val users = listeners[eventType]
        users?.remove(listener)
    }

    fun notify(
        eventType: String?,
        file: File?
    ) {
        val users = listeners[eventType]
        users?.forEach {
            it.update(eventType, file)
        }
    }
}

//This is event generator.
class Editor {

    val events: EventManager = EventManager("open", "save")

    private var file: File? = null

    fun openFile(filePath: String) {
        file = File(filePath)
        events.notify("open", file)
    }

    fun saveFile() {
        file?.let {
            events.notify("save", it)
        }
    }
}

class EmailNotificationListener(
    private val email: String
) : EventListener {

    override fun update(
        eventType: String?,
        file: File?
    ) {
        println("Email to $email: Someone has performed $eventType operation with the following file: ${file?.name}")
    }
}

class LogOpenListener(
    private val log: String
) : EventListener {

    override fun update(
        eventType: String?,
        file: File?
    ) {
        println("Save to log $log: Someone has performed $eventType operation with the following file: ${file?.name}")
    }
}

class ObserverTest {

    @Test
    fun testObserver() {

        val editor = Editor()

        val logListener = LogOpenListener("/path/to/log/file.txt")
        val emailListener = EmailNotificationListener("test@test.com")

        editor.events.subscribe("open", logListener)
        editor.events.subscribe("open", emailListener)
        editor.events.subscribe("save", emailListener)

        editor.openFile("test.txt")
        editor.saveFile()
    }
}

