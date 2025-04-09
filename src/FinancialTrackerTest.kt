package src

import src.console.FinancialTracker
import src.storage.MemoryFinancialTrackerStorage


fun main() {

    testCaseForDeleteTransaction()


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