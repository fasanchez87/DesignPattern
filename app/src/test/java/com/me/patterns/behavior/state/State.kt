package com.me.patterns.behavior.state

import org.junit.Assert.assertEquals
import org.junit.Test

//State Pattern.
sealed class AuthorizationState()

data object Unauthorized : AuthorizationState()

data class Authorized(val userName: String) : AuthorizationState()

class AuthorizationPresenter {

    private var state: AuthorizationState = Unauthorized

    fun login(userName: String) {
        state = Authorized(userName)
    }

    fun logout() {
        state = Unauthorized
    }

    fun isAuthorized(): Boolean {
        return state is Authorized
    }

    fun userName(): String {
        return when (state) {
            is Authorized -> (state as Authorized).userName
            else -> "Unknown"
        }
    }

    override fun toString(): String {
        return "User ${userName()} is authorized: ${isAuthorized()}"
    }
}

class TestState {

    @Test
    fun testState() {
        val presenter = AuthorizationPresenter()

        presenter.login("admin")
        println(presenter.isAuthorized())
        assertEquals(presenter.isAuthorized(), true)
        println(presenter.userName()) //user1
        presenter.logout()
        println(presenter.isAuthorized()) //false
        assertEquals(presenter.isAuthorized(), false)
        println(presenter.userName()) //Unknown
    }
}
