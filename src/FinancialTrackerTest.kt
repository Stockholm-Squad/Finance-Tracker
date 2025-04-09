package src

import java.io.DataInput
import java.io.IO.print
import java.io.IO.println


fun main() {

    fun updateTransactionTestCases(input: String): Boolean{
        return false
    }
    // Valid Update Test Case (ID)
    check(
        name = "When user update existing transaction should return true",
        result = updateTransactionTestCases("1"),
        correctResult = true
    )

    check(
        name = "When id has space start & end return true",
        result = updateTransactionTestCases(" 12345 "),
        correctResult = true
    )


    // InValid Update Test Case (ID)
    check(
        name = "When id has char should return false",
        result = updateTransactionTestCases("a"),
        correctResult = false
    )

    check(
        name = "When id has char at middle should return false",
        result = updateTransactionTestCases("123a45"),
        correctResult = false
    )

    check(
        name = "When id has space at middle should return false",
        result = updateTransactionTestCases("123 45"),
        correctResult = false
    )

    check(
        name = "When id has special character should return false",
        result = updateTransactionTestCases("123@45"),
        correctResult = false
    )

    check(
        name = "When id out of range should return false",
        result = updateTransactionTestCases("123456789"),
        correctResult = false
    )


    // Valid Update Test Case (valid selection)
    check(
        name = "When user enter valid selection",
        result = updateTransactionTestCases("1"),
        correctResult = true
    )

    check(
        name = "When enter space start & end return true",
        result = updateTransactionTestCases(" 1 "),
        correctResult = true
    )

    // InValid Update Test Case
    check(
        name = "When enter more than 1 digit should return false",
        result = updateTransactionTestCases("11"),
        correctResult = false
    )

    check(
        name = "When enter char should return false",
        result = updateTransactionTestCases("a"),
        correctResult = false
    )

    check(
        name = "When enter special character should return false",
        result = updateTransactionTestCases("@"),
        correctResult = false
    )

    check(
        name = "When enter out of range (1..4) should return false",
        result = updateTransactionTestCases("7"),
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