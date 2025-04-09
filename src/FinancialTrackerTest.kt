package src

import src.model.TransactionCheckInput
import src.storage.IFinancialTrackerStorage
import src.storage.MemoryFinancialTrackerStorage
import java.io.IO.println


fun main() {
    testUpdateTransaction()

}

fun testUpdateTransaction() {
    val financialTrackerStorage: IFinancialTrackerStorage = MemoryFinancialTrackerStorage()

    fun isValidTransactionId(id: String): Boolean {
        return true
    }

    fun isValidCheckInput(input: String): Boolean {
        return true
    }

    // region Update
    /**
     * Checkers for Valid (ID) */
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

    /**
     * Checkers for InValid (ID) */
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
        name = "When id out of range (<1)should return false",
        result = isValidTransactionId("0"),
        correctResult = false
    )

    check(
        name = "When id is empty should return false",
        result = isValidTransactionId(""),
        correctResult = false
    )

    /**
     * Checkers for Valid Check Input */
    check(
        name = "when update type input is valid should return true",
        result = isValidCheckInput("1"),
        correctResult = true
    )

    check(
        name = "when update type start & end with space should return true",
        result = isValidCheckInput(" 1 "),
        correctResult = true
    )

    /**
     * Checkers for InValid Check Input */
    check(
        name = "when update type out of range should return false",
        result = isValidCheckInput("7"),
        correctResult = false
    )

    check(
        name = "when update type is character should return false",
        result = isValidCheckInput("a"),
        correctResult = false
    )

    check(
        name = "when update type is special character should return false",
        result = isValidCheckInput("@"),
        correctResult = false
    )

    check(
        name = "when enter more than one digit should return false",
        result = isValidCheckInput("123456"),
        correctResult = false
    )

    check(
        name = "when input is empty should return false",
        result = isValidCheckInput(""),
        correctResult = false
    )

    /**
     * Checkers for Valid Transaction Category */
    check(
        name = "When category is valid should return true",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.CATEGORY, "Food"),
        correctResult = true
    )

    check(
        name = "When category start & end with space should return true",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.CATEGORY, " Food "),
        correctResult = true
    )

    check(
        name = "When category has space at middle should return true",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.CATEGORY, "Food Expenses"),
        correctResult = true
    )

    check(
        name = "When category has special character should return true",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.CATEGORY, "Fo@od"),
        correctResult = true
    )

    check(
        name = "When category has number should return true",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.CATEGORY, "F235ood"),
        correctResult = true
    )

    /**
     * Checkers for InValid Transaction Category */
    check(
        name = "When category is empty should return false",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.CATEGORY, ""),
        correctResult = false
    )


    /**
     * Checkers for Valid Transaction Type */
    check(
        name = "When type is valid should return true",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.TYPE, "1"),
        correctResult = true
    )

    check(
        name = "When type start & end with space should return true",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.TYPE, " 1 "),
        correctResult = true
    )

    /**
     * Checkers for Invalid Transaction Type */
    check(
        name = "When type has special char should return false",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.TYPE, "@"),
        correctResult = false
    )
    check(
        name = "When type is empty should return false",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.TYPE, ""),
        correctResult = false
    )
    check(
        name = "When type char should return false",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.TYPE, "a"),
        correctResult = false
    )

    check(
        name = "When type out of range should return false",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.TYPE, "3"),
        correctResult = false
    )

    /**
     * Checkers for Valid Transaction Amount */
    check(
        name = "when enter valid amount (Double value) should return true",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.AMOUNT, "5000.0"),
        correctResult = true
    )

    check(
        name = "when enter valid amount (Int value) should return true",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.AMOUNT, "5000"),
        correctResult = true
    )

    check(
        name = "when enter input start  & end with space should return true",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.AMOUNT, " 5000"),
        correctResult = true
    )

    /**
     * Checkers for InValid Transaction Amount */
    check(
        name = "when enter negative number should return false",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.AMOUNT, "-5569"),
        correctResult = false
    )

    check(
        name = "when enter character with number should return false",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.AMOUNT, "12aa"),
        correctResult = false
    )

    check(
        name = "when enter character should return false",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.AMOUNT, "aa"),
        correctResult = false
    )

    check(
        name = "when enter special character with number should return false",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.AMOUNT, "12#$2"),
        correctResult = false
    )

    check(
        name = "when enter valid amount (number with comma instead of dots) should return false",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.AMOUNT, "5,000,000"),
        correctResult = false
    )

    check(
        name = "when enter empty input should return false",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.AMOUNT, ""),
        correctResult = false
    )

    check(
        name = "when input has space at middle should return false",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.AMOUNT, "4,5 00"),
        correctResult = false
    )

    // endregion

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