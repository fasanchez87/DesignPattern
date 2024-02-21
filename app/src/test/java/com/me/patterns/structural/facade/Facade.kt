package com.me.patterns.structural.facade

import junit.framework.TestCase.assertEquals
import org.junit.Test

class ComplexSystemStore(
    private val filePath: String
){
    private val cache: HashMap<String, String> = HashMap()

    fun store(key: String, value: String){
        cache[key] = value
    }

    fun read(key: String): String {
        return cache[key]?:"without values"
    }

    fun commit() = println("Storing cache to file: $filePath")

}

data class User(
    val name: String
)

//Facade
class UserRepository {

    private val complexSystemStore = ComplexSystemStore("/data/default.prefs")

    fun save(user: User) = with(complexSystemStore){
        complexSystemStore.store("USER_KEY", user.name)
        complexSystemStore.commit()
    }

    fun getUsers() = complexSystemStore.read("USER_KEY")

}

//Facade Test
class FacadeTest {

    @Test
    fun testFacade(){
        val userRepository = UserRepository()
        userRepository.save(User("John"))
        userRepository.save(User("Martin"))
        assertEquals(userRepository.getUsers(), "Martin")
    }
}

