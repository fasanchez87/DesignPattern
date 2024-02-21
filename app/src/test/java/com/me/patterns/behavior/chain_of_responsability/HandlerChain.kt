package com.me.patterns.behavior.chain_of_responsability

import org.junit.Test

interface HandlerChain {
    fun addHeader(inputHeader: String): String
}

//Android use this pattern to handle the components of the view as a chain of responsibility: LinearLayout -> FrameLayout -> TextView
class AuthenticationHeader(
    val token: String?,
    var next: HandlerChain? = null
): HandlerChain {

    override fun addHeader(inputHeader: String) = "$inputHeader\nAuthorization: $token".let {
        next?.addHeader(it) ?: it
    }
}

class ContentTypeHeader(
    val contentType: String,
    var next: HandlerChain? = null
): HandlerChain {

    override fun addHeader(inputHeader: String) = "$inputHeader\nContent-Type: $contentType".let {
        next?.addHeader(it) ?: it
    }
}

class Body(
    val body: String,
    val next: HandlerChain? = null
): HandlerChain {

    override fun addHeader(inputHeader: String) = "$inputHeader\n$body".let {
        next?.addHeader(it) ?: it
    }
}

class ChainOfResponsibilityTest {

    @Test
    fun testChainOfResponsibility() {

        val autenticationHeader = AuthenticationHeader("Bearer123")
        val contentTypeHeader = ContentTypeHeader("application/json")
        val body = Body("{'name': 'John'}")

        autenticationHeader.next = contentTypeHeader
        contentTypeHeader.next = body

        val messageWithAuthentication = autenticationHeader.addHeader("Header with authentication")
        println(messageWithAuthentication)
        println("--------------------------")

        val messageWithoutAuthentication = contentTypeHeader.addHeader("Header without authentication")
        println(messageWithoutAuthentication)

    }

}