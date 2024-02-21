package com.me.patterns.behavior.visitor

import org.junit.Test

//Visitor Pattern.
//This pattern is used when we have to *perform an operation* on a group of similar kind of Objects.
interface ReportVisitor<out R> {
    fun visit(element: FixedPriceContract): R
    fun visit(element: TimeAndMaterialsContract): R
    fun visit(element: SupportContract): R
}

interface ReportElement {
    //This is visitor.
    //R is the return type of the visitor.
    fun <R> accept(visitor: ReportVisitor<R>): R
}

class FixedPriceContract(
    val costPerYear: Long
) : ReportElement {
    override fun <R> accept(visitor: ReportVisitor<R>): R {
        return visitor.visit(this)
    }
}

class TimeAndMaterialsContract(
    val costPerHour: Long,
    val hours: Long
) : ReportElement {
    override fun <R> accept(visitor: ReportVisitor<R>): R {
        return visitor.visit(this)
    }
}

class SupportContract(
    val costPerMonth: Long
) : ReportElement {
    override fun <R> accept(visitor: ReportVisitor<R>): R {
        return visitor.visit(this)
    }
}

class MonthlyCostReportVisitor: ReportVisitor<Long> {
    override fun visit(element: FixedPriceContract): Long {
        return element.costPerYear / 12
    }

    override fun visit(element: TimeAndMaterialsContract): Long {
        return element.costPerHour * element.hours
    }

    override fun visit(element: SupportContract): Long {
        return element.costPerMonth
    }
}

class YearlyCostReportVisitor : ReportVisitor<Long> {
    //Pattern visitor implements the operation to be performed on the elements of the object structure.
    override fun visit(element: FixedPriceContract): Long {
        return element.costPerYear
    }

    override fun visit(element: TimeAndMaterialsContract): Long {
        return element.costPerHour * element.hours
    }

    override fun visit(element: SupportContract): Long {
        return element.costPerMonth * 12
    }
}

class VisitorTest {

    @Test
    fun testVisitor() {

        val project = listOf(
            FixedPriceContract(1000),
            TimeAndMaterialsContract(100, 150),
            TimeAndMaterialsContract(100, 150),
            SupportContract(1000)
        )

        val monthlyCostReportVisitor = MonthlyCostReportVisitor()

        val monthlyCost = project.sumOf {
            it.accept(monthlyCostReportVisitor).toInt()
        }

        println("Monthly cost: $monthlyCost")

        val yearlyCostReportVisitor = YearlyCostReportVisitor()
        val yearlyCost = project.sumOf {
            it.accept(yearlyCostReportVisitor).toInt()
        }
        println("Yearly cost: $yearlyCost")
    }
}