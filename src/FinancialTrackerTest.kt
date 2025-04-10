package src

import model.Transaction
import storage.FinancialTrackerRepository
import storage.IFinancialTrackerStorage
import storage.MemoryFinancialTrackerStorage

fun main() {
    testCaseForDeleteTransaction()
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

fun testCaseForDeleteTransaction() {

    fun deleteTransactionById(id: String): Boolean{
        return false
    }

    check(
        name = "delete existing transaction (ex:id = 2) is should return true ",
        result = deleteTransactionById("2"),
        expectedResult = true
    )


    check(
        name = "invalid delete non-existing transaction id = 999 it should return false",
        result = deleteTransactionById("999"),
        expectedResult = false
    )

    check(
        name = "invalid delete transaction with negative ID id = -1 it should return false",
        result = deleteTransactionById("-1"),
        expectedResult = false
    )

    check(
        name = "invalid delete transaction with id 0 it should return false",
        result = deleteTransactionById("0"),
        expectedResult = false
    )


    check(
        name = "delete transaction but have no transaction(first time that run app) it should return false",
        result = deleteTransactionById("1"),
        expectedResult = false
    )

    check(
        name = "enter invalid id ex:user input = 1a it should return false",
        result = deleteTransactionById("1a"),
        expectedResult = false
    )

    check(
        name = "enter invalid id ex:user input = 5 but 5 not exist it should return false",
        result = deleteTransactionById("5"),
        expectedResult = false
    )

}

fun testGetTransactionMonthlyReport() {
    val financialTrackerStorage: IFinancialTrackerStorage = MemoryFinancialTrackerStorage()
    val financialTracker = FinancialTrackerRepository(financialTrackerStorage)

    /** Invalid test cases */

    check(
        name = "When month < 1 it should return null",
        result = getTransactionByMonth("-13") == null,
        expectedResult = true
    )

    check(
        name = "When month > 12 it should return null",
        result = getTransactionByMonth("13") == null,
        expectedResult = true
    )
    check(
        name = "When month is string it should return null",
        result = getTransactionByMonth("abc") == null,
        expectedResult = true
    )

    check(
        name = "When month is empty it should return null",
        result = getTransactionByMonth("") == null,
        expectedResult = true
    )

    check(
        name = "When month contains space between it should return null",
        result = getTransactionByMonth("1 1") == null,
        expectedResult = true
    )

    check(
        name = "When month is floating or double it should return null",
        result = getTransactionByMonth("1.0") == null,
        expectedResult = true
    )
    check(
        name = "When month starts with leading 0 it should return null",
        result = getTransactionByMonth("011") == null,
        expectedResult = true
    )
    check(
        name = "When month is not exist it should return null",
        result = getTransactionByMonth("12") == null,
        expectedResult = true
    )

    /** Valid test cases */
    check(
        name = "when month is exist for one time it should return value",
        result = getTransactionByMonth("1")?.isNotEmpty() == true,
        expectedResult = true
    )
    check(
        name = "when month is exist for 3 times it should return value",
        result = getTransactionByMonth("1")?.size == 3,
        expectedResult = true
    )

    check(
        name = "when month contains spaces around it should return value",
        result = getTransactionByMonth(" 2 ")?.isNotEmpty() == true,
        expectedResult = true
    )
    check(
        name = "when month is arabic digits it should return value",
        result = getTransactionByMonth("١")?.isNotEmpty() == true,
        expectedResult = true
    )
}

fun getTransactionByMonth(month: String): List<Transaction>? {
    return null
}