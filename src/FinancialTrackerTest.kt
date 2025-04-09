package src

import src.console.FinancialTracker
import src.storage.IFinancialTrackerStorage
import src.storage.MemoryFinancialTrackerStorage

fun main() {
    println("hello")
    testGetTransactionMonthlyReport()
}

fun check(name: String, result: Boolean, expectedResult: Boolean) {
    if (result == expectedResult) {
        println("Success $name")
    } else {
        println("Failed $name")
    }
}

fun testGetTransactionMonthlyReport() {
    val financialTrackerStorage: IFinancialTrackerStorage = MemoryFinancialTrackerStorage()
    val financialTracker = FinancialTracker(financialTrackerStorage)
    /** Invalid test cases */
    check(
        name = "When have negative number like -1 it should return false",
        result = financialTracker.getTransactionByMonth("-1") == null,
        expectedResult = true
    )
}