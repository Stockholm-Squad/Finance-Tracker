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
        name = "When id valid should return true",
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

    // Valid Category
    check(
        name = "When category is valid should return true",
        result = financialTrackerStorage.updateTransaction("1", TransactionUpdateType.CATEGORY, "Food"),
        correctResult = true
    )

    check(
        name = "When category start & end with space should return true",
        result = financialTrackerStorage.updateTransaction("1", TransactionUpdateType.CATEGORY, " Food "),
        correctResult = true
    )

    check(
        name = "When category is empty should return false",
        result = financialTrackerStorage.updateTransaction("1", TransactionUpdateType.CATEGORY, ""),
        correctResult = false
    )

    check(
        name = "When category has space at middle should return false",
        result = financialTrackerStorage.updateTransaction("1", TransactionUpdateType.CATEGORY, "Fo od"),
        correctResult = false
    )

    check(
        name = "When category has special character should return false",
        result = financialTrackerStorage.updateTransaction("1", TransactionUpdateType.CATEGORY, "Fo@od"),
        correctResult = false
    )

    check(
        name = "When category has number should return false",
        result = financialTrackerStorage.updateTransaction("1", TransactionUpdateType.CATEGORY, "F235ood"),
        correctResult = false
    )


    // Valid Transaction Type
    check(
        name = "When type is valid should return true",
        result = financialTrackerStorage.updateTransaction("1", TransactionUpdateType.TYPE, "1"),
        correctResult = true
    )

    check(
        name = "When type start & end with space should return true",
        result = financialTrackerStorage.updateTransaction("1", TransactionUpdateType.TYPE, " 1 "),
        correctResult = true
    )

    // InValid Type
    check(
        name = "When type more than 1 digit should return false",
        result = financialTrackerStorage.updateTransaction("1", TransactionUpdateType.TYPE, "12"),
        correctResult = false
    )

    check(
        name = "When type has special char should return false",
        result = financialTrackerStorage.updateTransaction("1", TransactionUpdateType.TYPE, "@"),
        correctResult = false
    )
    check(
        name = "When type is empty should return false",
        result = financialTrackerStorage.updateTransaction("1", TransactionUpdateType.TYPE, ""),
        correctResult = false
    )
    check(
        name = "When type char should return false",
        result = financialTrackerStorage.updateTransaction("1", TransactionUpdateType.TYPE, "a"),
        correctResult = false
    )

    check(
        name = "When type out of range should return false",
        result = financialTrackerStorage.updateTransaction("1", TransactionUpdateType.TYPE, "3"),
        correctResult = false
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