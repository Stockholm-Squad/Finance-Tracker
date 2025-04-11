package src

import src.model.Transaction
import src.model.TransactionDate
import src.model.TransactionMonth
import src.model.TransactionType
import src.storage.MemoryFinancialTrackerStorage
import src.update.validation.IUpdateTransactionActionValidator
import src.update.validation.UpdateTransactionActionValidator


fun testUpdateTransaction(updateTransactionActionValidator: IUpdateTransactionActionValidator) {

    // region ID

    println("\n-----------------  ID  --------------------")

    /**
     * Checkers for Valid (ID) */
    check(
        name = "When id is valid should return true",
        result = updateTransactionActionValidator.isValidateId("1"),
        expectedResult = true
    )

    check(
        name = "When id has empty space at start & end should return true",
        result = updateTransactionActionValidator.isValidateId(" 12 "),
        expectedResult = true
    )

    /**
     * Checkers for InValid (ID) */
    check(
        name = "When id has char should return false",
        result = updateTransactionActionValidator.isValidateId("a"),
        expectedResult = false
    )

    check(
        name = "When id has char at middle should return false",
        result = updateTransactionActionValidator.isValidateId("123a45"),
        expectedResult = false
    )

    check(
        name = "When id has space at middle should return false",
        result = updateTransactionActionValidator.isValidateId("123 45"),
        expectedResult = false
    )

    check(
        name = "When id has special character should return false",
        result = updateTransactionActionValidator.isValidateId("123@45"),
        expectedResult = false
    )

    check(
        name = "When id is out of range should return false",
        result = updateTransactionActionValidator.isValidateId("9874"),
        expectedResult = false
    )

    check(
        name = "When id is out of range id < 1 should return false",
        result = updateTransactionActionValidator.isValidateId("0"),
        expectedResult = false
    )

    check(
        name = "When id is empty should return false",
        result = updateTransactionActionValidator.isValidateId(""),
        expectedResult = false
    )

    // endregion

    // region Check Input

    println("\n--------------  Check Input ----------------")

    /**
     * Checkers for Valid Check Input */
    check(
        name = "when input is valid should return true",
        result = updateTransactionActionValidator.isValidOption("1"),
        expectedResult = true
    )

    check(
        name = "when input has empty space at start & end should return true",
        result = updateTransactionActionValidator.isValidOption(" 1 "),
        expectedResult = true
    )

    /**
     * Checkers for InValid Check Input */

    check(
        name = "when input has more than one digit should return false",
        result = updateTransactionActionValidator.isValidOption("123456"),
        expectedResult = false
    )

    check(
        name = "when input is out of range input > 5 should return false",
        result = updateTransactionActionValidator.isValidOption("6"),
        expectedResult = false
    )

    check(
        name = "when input is out of range input = 0 should return false",
        result = updateTransactionActionValidator.isValidOption("0"),
        expectedResult = false
    )

    check(
        name = "when input is out of range input < 1 should return false",
        result = updateTransactionActionValidator.isValidOption("-1"),
        expectedResult = false
    )

    check(
        name = "when input is character should return false",
        result = updateTransactionActionValidator.isValidOption("a"),
        expectedResult = false
    )

    check(
        name = "when input is special character should return false",
        result = updateTransactionActionValidator.isValidOption("@"),
        expectedResult = false
    )

    check(
        name = "when input is empty should return false",
        result = updateTransactionActionValidator.isValidOption(""),
        expectedResult = false
    )

    // endregion

    // region Category

    println("\n----------------  Category -----------------")

    /**
     * Checkers for Valid Transaction Category */
    check(
        name = "When category is valid should return true",
        result = updateTransactionActionValidator.isValidateCategory("Food"),
        expectedResult = true
    )

    check(
        name = "When category has empty space at start & end should return true",
        result = updateTransactionActionValidator.isValidateCategory(" Food "),
        expectedResult = true
    )

    check(
        name = "When category has space at middle should return true",
        result = updateTransactionActionValidator.isValidateCategory("Food Expenses"),
        expectedResult = true
    )

    check(
        name = "When category has special character should return true",
        result = updateTransactionActionValidator.isValidateCategory("Fo@od"),
        expectedResult = true
    )

    check(
        name = "When category has number should return true",
        result = updateTransactionActionValidator.isValidateCategory("F235ood"),
        expectedResult = true
    )

    /**
     * Checkers for InValid Transaction Category */
    check(
        name = "When category is empty should return false",
        result = updateTransactionActionValidator.isValidateCategory(""),
        expectedResult = false
    )

    // endregion

    // region Type

    println("\n------------------  Type  -------------------")

    /**
     * Checkers for Valid Transaction Type */
    check(
        name = "When type is valid should return true",
        result = updateTransactionActionValidator.isValidateType("1"),
        expectedResult = true
    )

    check(
        name = "When type has empty space at start & end should return true",
        result = updateTransactionActionValidator.isValidateType(" 2 "),
        expectedResult = true
    )

    /**
     * Checkers for Invalid Transaction Type */
    check(
        name = "When type has special char should return false",
        result = updateTransactionActionValidator.isValidateType("@"),
        expectedResult = false
    )

    check(
        name = "When type is empty should return false",
        result = updateTransactionActionValidator.isValidateType(""),
        expectedResult = false
    )

    check(
        name = "When type char should return false",
        result = updateTransactionActionValidator.isValidateType("a"),
        expectedResult = false
    )

    check(
        name = "When type out of range type > 2 should return false",
        result = updateTransactionActionValidator.isValidateType("3"),
        expectedResult = false
    )

    check(
        name = "When type out of range type = 0 should return false",
        result = updateTransactionActionValidator.isValidateType("0"),
        expectedResult = false
    )

    check(
        name = "When type out of range type < 1 should return false",
        result = updateTransactionActionValidator.isValidateType("-1"),
        expectedResult = false
    )

    // endregion

    // region Amount

    println("\n-----------------  Amount  ------------------")

    /**
     * Checkers for Valid Transaction Amount */
    check(
        name = "when amount is Double should return true",
        result = updateTransactionActionValidator.isValidateAmount("5000.0"),
        expectedResult = true
    )

    check(
        name = "when amount Int should return true",
        result = updateTransactionActionValidator.isValidateAmount("5000"),
        expectedResult = true
    )

    check(
        name = "when amount has empty space at start & end should return true",
        result = updateTransactionActionValidator.isValidateAmount(" 5000"),
        expectedResult = true
    )

    /**
     * Checkers for InValid Transaction Amount */
    check(
        name = "when amount < 1 should return false",
        result = updateTransactionActionValidator.isValidateAmount("-5569"),
        expectedResult = false
    )

    check(
        name = "when amount has car with number should return false",
        result = updateTransactionActionValidator.isValidateAmount("12aa"),
        expectedResult = false
    )

    check(
        name = "when amount has special character should return false",
        result = updateTransactionActionValidator.isValidateAmount("12#$2"),
        expectedResult = false
    )

    check(
        name = "when amount has comma instead of dots should return false",
        result = updateTransactionActionValidator.isValidateAmount("5,000,000"),
        expectedResult = false
    )

    check(
        name = "when amount is empty should return false",
        result = updateTransactionActionValidator.isValidateAmount(""),
        expectedResult = false
    )

    check(
        name = "when amount has space at middle should return false",
        result = updateTransactionActionValidator.isValidateAmount("4,5 00"),
        expectedResult = false
    )

    // endregion

    // region Date

    println("\n------------------  Date  -------------------")

    // Valid Date
    check(
        name = "When date is valid should return true",
        result = updateTransactionActionValidator.isValidateDate("28/02/2024"),
        expectedResult = true
    )

    check(
        name = "When leap year February 29th is valid should return true",
        result = updateTransactionActionValidator.isValidateDate("29/02/2024"), // Leap year
        expectedResult = true
    )

    check(
        name = "When date is valid for 31st of December should return true",
        result = updateTransactionActionValidator.isValidateDate("31/12/2025"),
        expectedResult = true
    )

    check(
        name = "When date is valid for 1st of January should return true",
        result = updateTransactionActionValidator.isValidateDate("01/01/2025"),
        expectedResult = true
    )

    // Invalid Date
    check(
        name = "When day exceeds days in month should return false",
        result = updateTransactionActionValidator.isValidateDate("32/03/2025"),
        expectedResult = false
    )

    check(
        name = "When negative day should return false",
        result = updateTransactionActionValidator.isValidateDate("-1/01/2025"),
        expectedResult = false
    )

    check(
        name = "When year is too early should return false",
        result = updateTransactionActionValidator.isValidateDate("01/01/999"),
        expectedResult = false
    )

    check(
        name = "When year is too late should return false",
        result = updateTransactionActionValidator.isValidateDate("01/01/2101"),
        expectedResult = false
    )

    // Month-specific invalid cases
    check(
        name = "When February day exceeds days in month should return false",
        result = updateTransactionActionValidator.isValidateDate("30/02/2025"),
        expectedResult = false
    )

    check(
        name = "When leap year February 29th is invalid should return false",
        result = updateTransactionActionValidator.isValidateDate("29/02/2023"), // 2023 is not a leap year
        expectedResult = false
    )

    check(
        name = "When day exceeds 30 days month (April) should return false",
        result = updateTransactionActionValidator.isValidateDate("31/04/2025"),
        expectedResult = false
    )

    check(
        name = "When day exceeds 30 days month (June) should return false",
        result = updateTransactionActionValidator.isValidateDate("31/06/2025"),
        expectedResult = false
    )

    check(
        name = "When day exceeds 30 days month (September) should return false",
        result = updateTransactionActionValidator.isValidateDate("31/09/2025"),
        expectedResult = false
    )

    check(
        name = "When day exceeds 30 days month (November) should return false",
        result = updateTransactionActionValidator.isValidateDate("31/11/2025"),
        expectedResult = false
    )

    // Validations for leap years
    check(
        name = "When leap year February 29th is valid should return true (on a leap year)",
        result = updateTransactionActionValidator.isValidateDate("29/02/2024"), // Leap year
        expectedResult = true
    )

    check(
        name = "When non-leap year February 29th is invalid should return false",
        result = updateTransactionActionValidator.isValidateDate("29/02/2023"), // Not a leap year
        expectedResult = false
    )

    // Invalid input format cases
    check(
        name = "When date contains letters should return false",
        result = updateTransactionActionValidator.isValidateDate("29/Feb/2024"),
        expectedResult = false
    )

    check(
        name = "When date contains slashes but no numbers should return false",
        result = updateTransactionActionValidator.isValidateDate("///2024"),
        expectedResult = false
    )

    check(
        name = "When date contains mixed characters (letters and numbers) should return false",
        result = updateTransactionActionValidator.isValidateDate("29/Feb/202X"),
        expectedResult = false
    )

    // Edge cases for the maximum valid year
    check(
        name = "When maximum valid year (2100) should return false",
        result = updateTransactionActionValidator.isValidateDate("01/01/2100"),
        expectedResult = true // This should be valid because 2100 is within the valid range
    )

    check(
        name = "When year exceeds valid range (2101) should return false",
        result = updateTransactionActionValidator.isValidateDate("01/01/2101"),
        expectedResult = false
    )

// endregion

}