package com.me.patterns.structural.adapter

import junit.framework.TestCase.assertEquals
import org.junit.Test

//------ Init 3rd party library functionally -----
data class DisplayDataType(
    val id: Float,
    val data: String
)

class DisplayData {

    fun displayData(displayDataType: DisplayDataType) {
        println("Displaying data: ${displayDataType.data}")
    }
}
// -------- End 3rd party library functionally -----


data class DatabaseData(
    val position: Int,
    val amount: Int
)

class DatabaseGenerator {

    fun generateData() = listOf(
            DatabaseData(1, 100),
            DatabaseData(2, 200),
            DatabaseData(3, 300)
        )
}

interface DatabaseAdapterInterface {
    fun getDisplayData(data: List<DatabaseData>): List<DisplayDataType>
}

class DatabaseAdapter(
    private val display: DisplayData
) : DatabaseAdapterInterface {

    //Here, we are using the 3rd party library to display the data and also converting the data to the 3rd party library format
    //This is the adapter pattern!
    override fun getDisplayData(data: List<DatabaseData>): List<DisplayDataType> {
        return data.map {
            DisplayDataType(
                id = it.position.toFloat(),
                data = "Amount: ${it.amount}"
            ).apply {
                display.displayData(this)
            }
        }
    }
}

class AdapterTest {

    @Test
    fun testAdapter() {

        val databaseData = DatabaseGenerator().generateData()

        val adapter = DatabaseAdapter(DisplayData())

        val convertData = adapter.getDisplayData(databaseData)

        assertEquals(convertData.size, 3)

    }
}

