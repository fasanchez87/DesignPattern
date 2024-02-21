package com.me.patterns.creational.lazyinitialization

import junit.framework.TestCase.assertEquals
import org.junit.Test

data class AlertBox(
    val message: String
) {
    fun showMessage() = "Message from alertBox: $message"
}

class AlertBoxLazy {

    private val alertBoxMessage = AlertBox("Hello, World!").showMessage()

    private val alertBox: AlertBox by lazy {
        AlertBox("Hello, World!")
    }

    fun showAlertBox() = alertBox.showMessage()
}

class AlertBoxLateInit {

    private lateinit var alertBox: AlertBox

    fun showAlertBox(): String {
        alertBox = AlertBox("Hello, World!")
        return alertBox.showMessage()
    }

}

class AlertBoxTest {

    @Test
    fun testAlertBox(){
        val alertBox = AlertBoxLazy()
        assertEquals(alertBox.showAlertBox(), "Message from alertBox: Hello, World!")
    }

    @Test
    fun testAlertBoxLateInit(){
        val alertBox = AlertBoxLateInit()
        assertEquals(alertBox.showAlertBox(), "Message from alertBox: Hello, World!")
    }
}