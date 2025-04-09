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

    /**
     * Checkers for Valid Date*/
    check(
        name = "when user enters valid date format dd/MM/yyyy",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "10/04/2025"),
        correctResult = true
    )

    check(
        name = "when user enters valid leap year date",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "29/02/2024"),
        correctResult = true
    )

    check(
        name = "when user enters valid date with single-digit day and month",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "1/1/2023"),
        correctResult = true
    )

    check(
        name = "when user enters valid date end of year",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "31/12/2023"),
        correctResult = true
    )

    check(
        name = "when user enters valid date with extra leading zeros",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "09/08/2022"),
        correctResult = true
    )

    /**
     * Checkers for InValid Date*/
    check(
        name = "when user enters invalid date format using dashes",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "2025-04-10"),
        correctResult = false
    )

    check(
        name = "when user enters day out of valid range",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "32/01/2024"),
        correctResult = false
    )

    check(
        name = "when user enters month out of valid range",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "15/13/2024"),
        correctResult = false
    )

    check(
        name = "when user enters year that is too small",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "15/05/0001"),
        correctResult = false
    )

    check(
        name = "when user enters letters instead of numbers in date",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "April/10/2025"),
        correctResult = false
    )

    check(
        name = "when user enters empty input for date",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, ""),
        correctResult = false
    )

    check(
        name = "when user enters date in wrong format yyyy-MM-dd",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "2025-04-10"),
        correctResult = false
    )

    check(
        name = "when user enters invalid date with slashes reversed",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "04\\10\\2025"),
        correctResult = false
    )

    check(
        name = "when user enters alphabet in date",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "aa/bb/cccc"),
        correctResult = false
    )

    check(
        name = "when user enters empty string for date",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, ""),
        correctResult = false
    )

    check(
        name = "when user enters date with missing parts",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "10/04"),
        correctResult = false
    )

    check(
        name = "when user enters invalid day in date",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "32/01/2023"),
        correctResult = false
    )

    check(
        name = "when user enters invalid month in date",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "10/13/2023"),
        correctResult = false
    )

    check(
        name = "when user enters invalid leap day in non-leap year",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "29/02/2023"),
        correctResult = false
    )

    check(
        name = "when user enters special characters in date",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "@1/0*/202#"),
        correctResult = false
    )

    check(
        name = "when user enters only spaces in date",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "   "),
        correctResult = false
    )

    check(
        name = "when user enters date with dash instead of slash",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "10-04-2025"),
        correctResult = false
    )

    check(
        name = "when user enters letters instead of numbers",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "dd/MM/yyyy"),
        correctResult = false
    )

    check(
        name = "when user enters date with month as word",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "10/April/2025"),
        correctResult = false
    )

    check(
        name = "when user enters date with extra slashes",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "10//04//2025"),
        correctResult = false
    )

    check(
        name = "when user enters date with missing year",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "10/04/"),
        correctResult = false
    )

    check(
        name = "when user enters date with negative values",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "-10/-04/2025"),
        correctResult = false
    )

    check(
        name = "when user enters date with zeros only",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "00/00/0000"),
        correctResult = false
    )

    check(
        name = "when user enters future date beyond 2100",
        result = financialTrackerStorage.updateTransaction(1, TransactionCheckInput.DATE, "10/04/2500"),
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