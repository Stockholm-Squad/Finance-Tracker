package src

import src.model.TransactionUpdateType
import src.storage.IFinancialTrackerStorage
import src.storage.MemoryFinancialTrackerStorage
import java.io.IO.println


fun main() {
    testUpdateTransaction()

}


fun testUpdateTransaction() {
    val financialTrackerStorage: IFinancialTrackerStorage = MemoryFinancialTrackerStorage()

    fun isValidTransactionId(id: String): Boolean {
        return financialTrackerStorage.getTransactionById(id) != null
    }

    // Valid (ID)
    check(
        name = "When user update existing transaction should return true",
        result = isValidTransactionId("1"),
        correctResult = true
    )

    check(
        name = "When id has space start & end return true",
        result = isValidTransactionId(" 12345 "),
        correctResult = true
    )

    // InValid (ID)
    check(
        name = "When id has char should return false",
        result = isValidTransactionId("a"),
        correctResult = false
    )

    check(
        name = "When id has char at middle should return false",
        result = isValidTransactionId("123a45"),
        correctResult = false
    )

    check(
        name = "When id has space at middle should return false",
        result = isValidTransactionId("123 45"),
        correctResult = false
    )

    check(
        name = "When id has special character should return false",
        result = isValidTransactionId("123@45"),
        correctResult = false
    )

    check(
        name = "When id out of range should return false",
        result = isValidTransactionId("123456789"),
        correctResult = false
    )

    check(
        name = "Update category successfully",
        result = financialTrackerStorage.updateTransaction("1", TransactionUpdateType.CATEGORY, "Groceries"),
        correctResult = true
    )






}

/**
 * Function to check the test case result and print success or failure.
 * @param name The name of the test case.
 * @param result The result obtained from the function.
 * @param correctResult The expected correct result.
 */
fun check(name: String, result: Boolean, correctResult: Boolean) {
    if (result == correctResult) {
        println("Success: $name")
    } else {
        println("Failed: $name")
    }
}