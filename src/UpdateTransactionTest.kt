package src

import src.update.validation.UpdateTransactionActionValidator

class UpdateTransactionTest : TransactionTest() {

    private val updateTransactionActionValidator = UpdateTransactionActionValidator()

    override fun runTest() {
        println("\n<-----------------  Update Transactions -------------------->")

        // region ID

        println("\n-----------------  ID  --------------------")

        /**
         * Checkers for Valid (ID) */
        check(
            name = "When id is valid should return true",
            result = updateTransactionActionValidator.isValidateIndex("1"),
            expectedResult = true
        )

        check(
            name = "When id has empty space at start & end should return true",
            result = updateTransactionActionValidator.isValidateIndex(" 12 "),
            expectedResult = true
        )

        /**
         * Checkers for InValid (ID) */
        check(
            name = "When id has char should return false",
            result = updateTransactionActionValidator.isValidateIndex("a"),
            expectedResult = false
        )

        check(
            name = "When id has char at middle should return false",
            result = updateTransactionActionValidator.isValidateIndex("123a45"),
            expectedResult = false
        )

        check(
            name = "When id has space at middle should return false",
            result = updateTransactionActionValidator.isValidateIndex("123 45"),
            expectedResult = false
        )

        check(
            name = "When id has special character should return false",
            result = updateTransactionActionValidator.isValidateIndex("123@45"),
            expectedResult = false
        )

        check(
            name = "When id is out of range should return false",
            result = updateTransactionActionValidator.isValidateIndex("9874"),
            expectedResult = false
        )

        check(
            name = "When id is out of range id < 1 should return false",
            result = updateTransactionActionValidator.isValidateIndex("0"),
            expectedResult = false
        )

        check(
            name = "When id is empty should return false",
            result = updateTransactionActionValidator.isValidateIndex(""),
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

        // Valid Day
        check(
            name = "When valid day (15) should return true",
            result = updateTransactionActionValidator.isValidateDay("15"),
            expectedResult = true
        )

        check(
            name = "When day is 31 (maximum valid day) should return true",
            result = updateTransactionActionValidator.isValidateDay("31"),
            expectedResult = true
        )

        check(
            name = "When day is '05' (should be valid as day 5)",
            result = updateTransactionActionValidator.isValidateDay("05"),
            expectedResult = true
        )

        // InValid Day
        check(
            name = "When day is less than 1 should return false",
            result = updateTransactionActionValidator.isValidateDay("0"),
            expectedResult = false
        )

        check(
            name = "When day exceeds 31 should return false",
            result = updateTransactionActionValidator.isValidateDay("32"),
            expectedResult = false
        )

        check(
            name = "When day is negative should return false",
            result = updateTransactionActionValidator.isValidateDay("-5"),
            expectedResult = false
        )

        // Valid Month
        check(
            name = "When valid month (5) should return true",
            result = updateTransactionActionValidator.isValidateMonth("5"),
            expectedResult = true
        )

        check(
            name = "When month is 12 (maximum valid month) should return true",
            result = updateTransactionActionValidator.isValidateMonth("12"),
            expectedResult = true
        )

        check(
            name = "When month is '05' (should be valid as month 5)",
            result = updateTransactionActionValidator.isValidateMonth("05"),
            expectedResult = true
        )

        // InValid Month
        check(
            name = "When month is less than 1 should return false",
            result = updateTransactionActionValidator.isValidateMonth("0"),
            expectedResult = false
        )

        check(
            name = "When month exceeds 12 should return false",
            result = updateTransactionActionValidator.isValidateMonth("13"),
            expectedResult = false
        )

        check(
            name = "When month is negative should return false",
            result = updateTransactionActionValidator.isValidateMonth("-3"),
            expectedResult = false
        )

    // endregion

    }
}