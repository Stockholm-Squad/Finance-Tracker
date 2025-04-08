package src

import src.model.Transaction
import src.storage.MemoryFinancialTrackerStorage


fun main() {
    print("hello")

    // start region for view transaction
    // get transaction by id

    val memoryFinancialTrackerStorage = MemoryFinancialTrackerStorage()
    check(
        name = "when user send valid id, return true",
        result = memoryFinancialTrackerStorage.getTransactionById(2) != null,
        expectedResult = true
    )
    check(
        name = "when user send out of range id, return false",
        result = memoryFinancialTrackerStorage.getTransactionById(100) != null,
        expectedResult = false
    )

    check(
        name = "when user send out of range and negative id, return false",
        result = memoryFinancialTrackerStorage.getTransactionById(-1) != null,
        expectedResult = false
    )
    check(
        name = "when user send zero, return false",
        result = memoryFinancialTrackerStorage.getTransactionById(0) != null,
        expectedResult = false
    )


    // get all transaction

    val listSize = memoryFinancialTrackerStorage.getAllTransactions()?.size
    check(
        name = "when transaction list has data, return true",
        result = listSize != null && listSize != 0,
        expectedResult = true
    )
    check(
        name = "when list in init but has no data, return true",
        result = listSize == 0,
        expectedResult = true
    )
    check(
        name = "when transaction list is empty, return true",
        result = memoryFinancialTrackerStorage.getAllTransactions() == emptyList<Transaction>(),
        expectedResult = true
    )

    // end region for view transaction
}


fun check(name: String, result: Boolean, expectedResult: Boolean) {
    if (result == expectedResult) {
        println("Success $name")
    } else {
        println("Failed $name")
    }
}