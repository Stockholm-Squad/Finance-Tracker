package src

import src.console.FinancialTracker
import src.storage.IFinancialTrackerStorage
import src.storage.MemoryFinancialTrackerStorage
import src.test.TransactionMonthlyReportTestCase


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
    check(
        name = TransactionMonthlyReportTestCase.TEST_CASE1.testCaseMessage,
        result = financialTracker.getTransactionByMonth(TransactionMonthlyReportTestCase.TEST_CASE1.month) == null,
        expectedResult = true
    )
}