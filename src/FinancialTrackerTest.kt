package src

import src.console.FinancialTracker
import src.storage.MemoryFinancialTrackerStorage


fun main() {

    // rejoin delete
    val financialTracker = FinancialTracker(MemoryFinancialTrackerStorage())
    val storage= financialTracker.iFinancialTrackerStorage
    check(
        name = "delete existing transaction id = 2",
        result = storage.deleteTransactionById(2),
        expectedResult = true
    )

    check(
        name = "delete first transaction id = 1",
        result = storage.deleteTransactionById(1),
        expectedResult = true
    )

    check(
        name = "delete transaction from middle id = 3",
        result = storage.deleteTransactionById(3),
        expectedResult = true
    )

    check(
        name = "delete newly added transaction id = 4",
        result = storage.deleteTransactionById(4),
        expectedResult = true
    )

    check(
        name = "delete last transaction id = 5",
        result = storage.deleteTransactionById(5),
        expectedResult = true
    )

    check(
        name = "invalid delete non-existing transaction id = 999",
        result = storage.deleteTransactionById(999),
        expectedResult = false
    )

    check(
        name = "invalid delete transaction with negative ID id = -1",
        result = storage.deleteTransactionById(-1),
        expectedResult = false
    )

    check(
        name = "invalid delete transaction with id 0",
        result = storage.deleteTransactionById(0),
        expectedResult = false
    )


    check(
        name = "invalid delete transaction with invalid id non-integer",
        result = storage.deleteTransactionById(null as Int),
        expectedResult = false
    )

}

fun check(name: String, result: Boolean, expectedResult: Boolean) {
    if (result == expectedResult) {
        println("Success $name")
    } else {
        println("Failed $name")
    }
}