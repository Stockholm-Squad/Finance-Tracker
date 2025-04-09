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
        name = "When have negative number like -1 it should return null",
        result = financialTracker.getTransactionByMonth("-1") == null,
        expectedResult = true
    )
    check(
        name = "When have  number < 1 like -13 it should return null",
        result = financialTracker.getTransactionByMonth("-13") == null,
        expectedResult = true
    )
    check(
        name = "When have number == 0 it should return null",
        result = financialTracker.getTransactionByMonth("0") == null,
        expectedResult = true
    )
    check(
        name = "When have  number > 12 it should return null",
        result = financialTracker.getTransactionByMonth("13") == null,
        expectedResult = true
    )
    check(
        name = "When have string like abc it should return null",
        result = financialTracker.getTransactionByMonth("abc") == null,
        expectedResult = true
    )
    check(
        name = "When have charchter like A it should return null",
        result = financialTracker.getTransactionByMonth("A") == null,
        expectedResult = true
    )
    check(
        name = "When have empty String it should return null",
        result = financialTracker.getTransactionByMonth("") == null,
        expectedResult = true
    )
    check(
        name = "When have special char like , it should return null",
        result = financialTracker.getTransactionByMonth(",") == null,
        expectedResult = true
    )
    check(
        name = "When have space between number like 1 1 it should return null",
        result = financialTracker.getTransactionByMonth("1 1") == null,
        expectedResult = true
    )
    check(
        name = "When have leading 0 with number two digit 011 it should return null",
        result = financialTracker.getTransactionByMonth("011") == null,
        expectedResult = true
    )
    check(
        name = "When month is not exist in memory {1,2,3} it should return null",
        result = financialTracker.getTransactionByMonth("12") == null,
        expectedResult = true
    )
    check(
        name = "When memory is empty it should return null",
        result = financialTracker.getTransactionByMonth("1") == null,
        expectedResult = true
    )
}