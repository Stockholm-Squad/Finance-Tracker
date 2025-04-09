package src

import src.console.FinancialTracker
import src.model.Transaction
import src.storage.IFinancialTrackerStorage
import src.storage.MemoryFinancialTrackerStorage


fun main() {
    print("hello")

    // start region for view transaction
    // get transaction by id


    check(
        name = "when user send valid id, return true",
        result = getTransactionById("2") != null,
        expectedResult = true
    )
    check(
        name = "when user send out of range id, return false",
        result = getTransactionById("100") != null,
        expectedResult = false
    )

    check(
        name = "when user send out of range and negative id, return false",
        result = getTransactionById("-1") != null,
        expectedResult = false
    )
    check(
        name = "when user send zero, return false",
        result = getTransactionById("0") != null,
        expectedResult = false
    )
    check(
        name = "when user send enter wrong input, return false",
        result = getTransactionById("a") != null,
        expectedResult = false
    )

    check(
        name = "when user send enter 0 before choice, return false",
        result = getTransactionById("01") != null,
        expectedResult = false
    )
    check(
        name = "when user send enter empty input, return false",
        result = getTransactionById("") != null,
        expectedResult = false
    )




    // get all transaction

    val listSize = getAllTransaction()?.size
    check(
        name = "when transaction list has data, return true",
        result = listSize != null && listSize != 0,
        expectedResult = true
    )
    check(
        name = "when list in init but has no data, return true", result = listSize == 0, expectedResult = true
    )
    check(
        name = "when transaction list is empty, return true",
        result = getAllTransaction() == emptyList<Transaction>(),
        expectedResult = true
    )

    // end region for view transaction
}


fun getTransactionById(id: String): Transaction? {
    val memoryFinancialTrackerStorage: IFinancialTrackerStorage = MemoryFinancialTrackerStorage()
    val financialTracker = FinancialTracker(memoryFinancialTrackerStorage)

    return null
}

fun getAllTransaction(): List<Transaction>? {
    return null
}


fun check(name: String, result: Boolean, expectedResult: Boolean) {
    if (result == expectedResult) {
        println("Success $name")
    } else {
        println("Failed $name")
    }
}