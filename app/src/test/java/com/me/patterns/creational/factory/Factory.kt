package com.me.patterns.creational.factory

import junit.framework.TestCase.assertEquals
import org.junit.Test

sealed class Country {
    data object Canada : Country()
    data object Spain: Country()
    data class Greece(val someProperty: String) : Country()
}

//Advantages of using sealed class is that it is a good way to represent restricted class hierarchies and
//when used with when expression, it helps to ensure that all cases are covered,
//as well, we can extend the sealed class with others data classes, objects, and other sealed classes.
data class Usa(
    val id: String,
    val someProperty: String
) : Country()

data class Colombia(
    val id: String,
    val someProperty: String,
    val otherProperty: String
) : Country()

enum class CurrencyCode(
    val code: String,
    val currencyName: String
) {
    USD("USD", "Dollar"),
    EUR("EUR", "Euro"),
    PESOS("PESOS", "Peso"),
    CAD("CAD", "Dollar Canada");

    companion object {
        fun getCurrencyCode(code: String) = entries.firstOrNull { it.code == code } ?: throw IllegalArgumentException("Currency code not found")
    }
}

object CurrencyFactory {
    //Factory method to create a currency for a given country.
    fun currencyForCountry(country: Country) = when (country) {
        is Country.Canada -> CurrencyCode.CAD
        is Country.Spain -> CurrencyCode.EUR
        is Country.Greece -> CurrencyCode.EUR
        is Usa -> CurrencyCode.USD
        is Colombia -> CurrencyCode.PESOS
    }
}

class FactoryTest {

    @Test
    fun whenGiveUSACountryThenReturnYourCorrectCurrency() {
        val usa = Usa("Usa", "My currency is USD")
        val currencyUsa = CurrencyFactory.currencyForCountry(usa)
        assertEquals(currencyUsa.currencyName, CurrencyCode.USD.currencyName)
    }

    @Test
    fun whenGiveCanadaCountryThenReturnYourCorrectCurrency() {
        val canada = Country.Canada
        val currencyCanada = CurrencyFactory.currencyForCountry(canada)
        assertEquals(currencyCanada.currencyName, CurrencyCode.CAD.currencyName)
    }

    @Test
    fun whenGiveSpainCountryThenReturnYourCorrectCurrency() {
        val spain = Country.Spain
        val currencySpain = CurrencyFactory.currencyForCountry(spain)
        assertEquals(currencySpain.currencyName, CurrencyCode.EUR.currencyName)
    }

    @Test
    fun whenGiveGreeceCountryThenReturnYourCorrectCurrency() {
        val greece = Country.Greece("My currency is EUR")
        val currencyGreece = CurrencyFactory.currencyForCountry(greece)
        assertEquals(currencyGreece.currencyName, CurrencyCode.EUR.currencyName)
    }

    @Test(expected = IllegalArgumentException::class)
    fun whenGiveCountryNotExistsThenReturnException() {
        CurrencyCode.getCurrencyCode("XXX")
    }
}

