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

    fun isValidUpdateTypeInput(input: String): Boolean{
        val inputt : Int = input.toInt()
        if (inputt in 1..4){
            return true
        } else {
            return false
        }
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

    //check update type
    //valid case
    check(
        name = "when update type input is valid should return true",
        result = isValidUpdateTypeInput("1"),
        correctResult = true
    )

    check(
        name = "when update type start with space should return true",
        result = isValidUpdateTypeInput(" 1"),
        correctResult = true
    )

    check(
        name = "when update type end with space should return true",
        result = isValidUpdateTypeInput("1 "),
        correctResult = true
    )


    //invalid cases

    check(
        name = "when update type out of range should return false",
        result = isValidUpdateTypeInput("7"),
        correctResult = false
    )

    check(
        name = "when update type is character should return false",
        result = isValidUpdateTypeInput("a"),
        correctResult = false
    )

    check(
        name = "when update type is special character should return false",
        result = isValidUpdateTypeInput("@"),
        correctResult = false
    )

    check(
        name = "when enter more than one digit should return false",
        result = isValidUpdateTypeInput("123456"),
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