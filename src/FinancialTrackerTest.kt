package src

import src.model.Transaction
import src.model.TransactionCheckInput
import src.storage.IFinancialTrackerStorage
import src.storage.MemoryFinancialTrackerStorage

fun main() {
    testUpdateTransaction()

// region add
    /**
     * Checkers for amount parameter*/
    check(
        "when amount is less than 0 then return false",
        addTransaction(amount = "-1", day = "2", month = "2", year = "2025", type = "1", category = "food"),
        false
    )
    check(
        "when amount is empty then return false",
        addTransaction(amount = "", day = "2", month = "2", year = "2025", type = "1", category = "food"),
        false
    )
    check(
        "when amount contains characters then return false",
        addTransaction(amount = "A", day = "2", month = "2", year = "2025", type = "1", category = "food"),
        false
    )
    check(
        "when amount contains special characters then return false",
        addTransaction(amount = "@", day = "2", month = "2", year = "2025", type = "1", category = "food"),
        false
    )
    check(
        "when amount is equals 0 then return true",
        addTransaction(amount = "0", day = "2", month = "2", year = "2025", type = "1", category = "food"),
        true
    )
    check(
        "when amount is greater 0 then return true",
        addTransaction(amount = "2", day = "2", month = "2", year = "2025", type = "1", category = "food"),
        true
    )
    /**
     * Checkers for day parameter*/
    check(
        "when day equals 0 then return false",
        addTransaction(amount = "23", day = "0", month = "2", year = "2025", type = "1", category = "food"),
        false
    )
    check(
        "when day is a negative number then return false",
        addTransaction(amount = "23", day = "-1", month = "02", year = "2025", type = "1", category = "food"),
        false
    )
    check(
        "when day is greater than 31 then return false",
        addTransaction(amount = "23", day = "32", month = "2", year = "2025", type = "1", category = "food"),
        false
    )
    check(
        "when day is empty then return false",
        addTransaction(amount = "23", day = "", month = "2", year = "2025", type = "1", category = "food"),
        false
    )
    check(
        "when day contains characters return false",
        addTransaction(amount = "23", day = "sunday", month = "2", year = "2025", type = "1", category = "food"),
        false
    )
    check(
        "when day contains leading zeros then return false",
        addTransaction(amount = "03", day = "23", month = "2", year = "2025", type = "1", category = "food"),
        false
    )
    check(
        "when day value less 32 and greater than 0 return true",
        addTransaction(amount = "23", day = "31", month = "2", year = "2025", type = "1", category = "food"),
        true
    )
    /**
     * Checkers for month parameter*/
    check(
        "when month equals 0 then return false",
        addTransaction(amount = "23", day = "23", month = "0", year = "2025", type = "1", category = "food"),
        false
    )
    check(
        "when month is a negative number then return false",
        addTransaction(amount = "23", day = "23", month = "-1", year = "2025", type = "1", category = "food"),
        false
    )
    check(
        "when month is greater than 12 then return false",
        addTransaction(amount = "23", day = "23", month = "13", year = "2025", type = "1", category = "food"),
        false
    )
    check(
        "when day is empty then return false",
        addTransaction(amount = "23", day = "23", month = "", year = "2025", type = "1", category = "food"),
        false
    )
    check(
        "when day contains characters return false",
        addTransaction(amount = "23", day = "23", month = "A", year = "2025", type = "1", category = "food"),
        false
    )
    check(
        "when month contains leading zeros then return false",
        addTransaction(amount = "23", day = "23", month = "02", year = "2025", type = "1", category = "food"),
        false
    )
    check(
        "when month value less 13 and greater than 0 return true",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", type = "1", category = "food"),
        true
    )
    /**
     * Checkers for year parameter*/
    check(
        "when year equals 0 then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "0", type = "1", category = "food"),
        false
    )
    check(
        "when year is a negative number then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "0", type = "1", category = "food"),
        false
    )
    check(
        "when year is greater than current year then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2026", type = "1", category = "food"),
        false
    )
    check(
        "when year is less than current year then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2024", type = "1", category = "food"),
        false
    )
    check(
        "when year is empty then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "", type = "1", category = "food"),
        false
    )
    check(
        "when year contains characters return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "a", type = "1", category = "food"),
        false
    )
    check(
        "when year contains leading zeros then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "0200", type = "1", category = "food"),
        false
    )
    check(
        "when year value is current year then return true",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", type = "1", category = "food"),
        true
    )
    /**
     * Checkers for type parameter*/
    check(
        "when type equals 0 then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", type = "0", category = "food"),
        false
    )
    check(
        "when type is a negative number then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", type = "-1", category = "food"),
        false
    )
    check(
        "when type is greater than 2 then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", type = "3", category = "food"),
        false
    )
    check(
        "when type is empty then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", type = "", category = "food"),
        false
    )
    check(
        "when type contains characters return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", type = "A", category = "food"),
        false
    )
    check(
        "when year contains leading zeros then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", type = "01", category = "food"),
        false
    )
    check(
        "when year value less than 3 and greater than 0 then return true",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", type = "1", category = "food"),
        true
    )

    /**
     * Checkers for category parameter*/
    check(
        "when category contains numbers then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", type = "1", category = "2food"),
        false
    )
    check(
        "when category is empty then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", type = "1", category = ""),
        false
    )
    check(
        "when category contains special characters return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", type = "1", category = "f@ood"),
        false
    )
    check(
        "when category characters less than 2 return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", type = "1", category = "fo"),
        false
    )
    check(
        "when category characters greater than 25 return false",
        addTransaction(
            amount = "23",
            day = "23",
            month = "2",
            year = "2025",
            type = "1",
            category = "foodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfood"
        ),
        false
    )
    check(
        "when category value is a valid value return true",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", type = "1", category = "food"),
        true
    )
// endregion

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

    print("hello")
}

fun getTransactionById(id: String): Transaction? {
    val memoryFinancialTrackerStorage: IFinancialTrackerStorage = MemoryFinancialTrackerStorage()

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

fun addTransaction(amount: String, day: String, month: String, year: String, type: String, category: String): Boolean {

    return false
}


fun testUpdateTransaction() {
    fun isValidTransactionId(id: String): Boolean {
        return true
    }

    fun isValidCheckInput(input: String): Boolean {
        return true
    }

    fun updateTransaction(transactionId: Int, transactionCheckInput: TransactionCheckInput, newValue: String): Boolean {
        return false
    }

    // region Update
    /**
     * Checkers for Valid (ID) */
    check(
        name = "When id valid should return true",
        result = isValidTransactionId("1"),
        expectedResult = true
    )

    check(
        name = "When id has space start & end return true",
        result = isValidTransactionId(" 12345 "),
        expectedResult = true
    )

    /**
     * Checkers for InValid (ID) */
    check(
        name = "When id has char should return false",
        result = isValidTransactionId("a"),
        expectedResult = false
    )

    check(
        name = "When id has char at middle should return false",
        result = isValidTransactionId("123a45"),
        expectedResult = false
    )

    check(
        name = "When id has space at middle should return false",
        result = isValidTransactionId("123 45"),
        expectedResult = false
    )

    check(
        name = "When id has special character should return false",
        result = isValidTransactionId("123@45"),
        expectedResult = false
    )

    check(
        name = "When id out of range should return false",
        result = isValidTransactionId("123456789"),
        expectedResult = false
    )

    check(
        name = "When id out of range (<1)should return false",
        result = isValidTransactionId("0"),
        expectedResult = false
    )

    check(
        name = "When id is empty should return false",
        result = isValidTransactionId(""),
        expectedResult = false
    )

    /**
     * Checkers for Valid Check Input */
    check(
        name = "when update type input is valid should return true",
        result = isValidCheckInput("1"),
        expectedResult = true
    )

    check(
        name = "when update type start & end with space should return true",
        result = isValidCheckInput(" 1 "),
        expectedResult = true
    )

    /**
     * Checkers for InValid Check Input */
    check(
        name = "when update type out of range should return false",
        result = isValidCheckInput("7"),
        expectedResult = false
    )

    check(
        name = "when update type is character should return false",
        result = isValidCheckInput("a"),
        expectedResult = false
    )

    check(
        name = "when update type is special character should return false",
        result = isValidCheckInput("@"),
        expectedResult = false
    )

    check(
        name = "when enter more than one digit should return false",
        result = isValidCheckInput("123456"),
        expectedResult = false
    )

    check(
        name = "when input is empty should return false",
        result = isValidCheckInput(""),
        expectedResult = false
    )

    /**
     * Checkers for Valid Transaction Category */
    check(
        name = "When category is valid should return true",
        result = updateTransaction(1, TransactionCheckInput.CATEGORY, "Food"),
        expectedResult = true
    )

    check(
        name = "When category start & end with space should return true",
        result = updateTransaction(1, TransactionCheckInput.CATEGORY, " Food "),
        expectedResult = true
    )

    check(
        name = "When category has space at middle should return true",
        result = updateTransaction(1, TransactionCheckInput.CATEGORY, "Food Expenses"),
        expectedResult = true
    )

    check(
        name = "When category has special character should return true",
        result = updateTransaction(1, TransactionCheckInput.CATEGORY, "Fo@od"),
        expectedResult = true
    )

    check(
        name = "When category has number should return true",
        result = updateTransaction(1, TransactionCheckInput.CATEGORY, "F235ood"),
        expectedResult = true
    )

    /**
     * Checkers for InValid Transaction Category */
    check(
        name = "When category is empty should return false",
        result = updateTransaction(1, TransactionCheckInput.CATEGORY, ""),
        expectedResult = false
    )


    /**
     * Checkers for Valid Transaction Type */
    check(
        name = "When type is valid should return true",
        result = updateTransaction(1, TransactionCheckInput.TYPE, "1"),
        expectedResult = true
    )

    check(
        name = "When type start & end with space should return true",
        result = updateTransaction(1, TransactionCheckInput.TYPE, " 1 "),
        expectedResult = true
    )

    /**
     * Checkers for Invalid Transaction Type */
    check(
        name = "When type has special char should return false",
        result = updateTransaction(1, TransactionCheckInput.TYPE, "@"),
        expectedResult = false
    )

    check(
        name = "When type is empty should return false",
        result = updateTransaction(1, TransactionCheckInput.TYPE, ""),
        expectedResult = false
    )

    check(
        name = "When type char should return false",
        result = updateTransaction(1, TransactionCheckInput.TYPE, "a"),
        expectedResult = false
    )

    check(
        name = "When type out of range should return false",
        result = updateTransaction(1, TransactionCheckInput.TYPE, "3"),
        expectedResult = false
    )

    /**
     * Checkers for Valid Transaction Amount */
    check(
        name = "when enter valid amount (Double value) should return true",
        result = updateTransaction(1, TransactionCheckInput.AMOUNT, "5000.0"),
        expectedResult = true
    )

    check(
        name = "when enter valid amount (Int value) should return true",
        result = updateTransaction(1, TransactionCheckInput.AMOUNT, "5000"),
        expectedResult = true
    )

    check(
        name = "when enter input start  & end with space should return true",
        result = updateTransaction(1, TransactionCheckInput.AMOUNT, " 5000"),
        expectedResult = true
    )

    /**
     * Checkers for InValid Transaction Amount */
    check(
        name = "when enter negative number should return false",
        result = updateTransaction(1, TransactionCheckInput.AMOUNT, "-5569"),
        expectedResult = false
    )

    check(
        name = "when enter character with number should return false",
        result = updateTransaction(1, TransactionCheckInput.AMOUNT, "12aa"),
        expectedResult = false
    )

    check(
        name = "when enter character should return false",
        result = updateTransaction(1, TransactionCheckInput.AMOUNT, "aa"),
        expectedResult = false
    )

    check(
        name = "when enter special character with number should return false",
        result = updateTransaction(1, TransactionCheckInput.AMOUNT, "12#$2"),
        expectedResult = false
    )

    check(
        name = "when enter valid amount (number with comma instead of dots) should return false",
        result = updateTransaction(1, TransactionCheckInput.AMOUNT, "5,000,000"),
        expectedResult = false
    )

    check(
        name = "when enter empty input should return false",
        result = updateTransaction(1, TransactionCheckInput.AMOUNT, ""),
        expectedResult = false
    )

    check(
        name = "when input has space at middle should return false",
        result = updateTransaction(1, TransactionCheckInput.AMOUNT, "4,5 00"),
        expectedResult = false
    )

    /**
     * Checkers for Valid Date*/
    check(
        name = "when user enters valid date format dd/MM/yyyy",
        result = updateTransaction(1, TransactionCheckInput.DATE, "10/04/2025"),
        expectedResult = true
    )

    check(
        name = "when user enters valid leap year date",
        result = updateTransaction(1, TransactionCheckInput.DATE, "29/02/2024"),
        expectedResult = true
    )

    check(
        name = "when user enters valid date with single-digit day and month",
        result = updateTransaction(1, TransactionCheckInput.DATE, "1/1/2023"),
        expectedResult = true
    )

    check(
        name = "when user enters valid date end of year",
        result = updateTransaction(1, TransactionCheckInput.DATE, "31/12/2023"),
        expectedResult = true
    )

    check(
        name = "when user enters valid date with extra leading zeros",
        result = updateTransaction(1, TransactionCheckInput.DATE, "09/08/2022"),
        expectedResult = true
    )

    /**
     * Checkers for InValid Date*/
    check(
        name = "when user enters invalid date format using dashes",
        result = updateTransaction(1, TransactionCheckInput.DATE, "2025-04-10"),
        expectedResult = false
    )

    check(
        name = "when user enters day out of valid range",
        result = updateTransaction(1, TransactionCheckInput.DATE, "32/01/2024"),
        expectedResult = false
    )

    check(
        name = "when user enters month out of valid range",
        result = updateTransaction(1, TransactionCheckInput.DATE, "15/13/2024"),
        expectedResult = false
    )

    check(
        name = "when user enters year that is too small",
        result = updateTransaction(1, TransactionCheckInput.DATE, "15/05/0001"),
        expectedResult = false
    )

    check(
        name = "when user enters letters instead of numbers in date",
        result = updateTransaction(1, TransactionCheckInput.DATE, "April/10/2025"),
        expectedResult = false
    )

    check(
        name = "when user enters empty input for date",
        result = updateTransaction(1, TransactionCheckInput.DATE, ""),
        expectedResult = false
    )

    check(
        name = "when user enters date in wrong format yyyy-MM-dd",
        result = updateTransaction(1, TransactionCheckInput.DATE, "2025-04-10"),
        expectedResult = false
    )

    check(
        name = "when user enters invalid date with slashes reversed",
        result = updateTransaction(1, TransactionCheckInput.DATE, "04\\10\\2025"),
        expectedResult = false
    )

    check(
        name = "when user enters alphabet in date",
        result = updateTransaction(1, TransactionCheckInput.DATE, "aa/bb/cccc"),
        expectedResult = false
    )

    check(
        name = "when user enters empty string for date",
        result = updateTransaction(1, TransactionCheckInput.DATE, ""),
        expectedResult = false
    )

    check(
        name = "when user enters date with missing parts",
        result = updateTransaction(1, TransactionCheckInput.DATE, "10/04"),
        expectedResult = false
    )

    check(
        name = "when user enters invalid day in date",
        result = updateTransaction(1, TransactionCheckInput.DATE, "32/01/2023"),
        expectedResult = false
    )

    check(
        name = "when user enters invalid month in date",
        result = updateTransaction(1, TransactionCheckInput.DATE, "10/13/2023"),
        expectedResult = false
    )

    check(
        name = "when user enters invalid leap day in non-leap year",
        result = updateTransaction(1, TransactionCheckInput.DATE, "29/02/2023"),
        expectedResult = false
    )

    check(
        name = "when user enters special characters in date",
        result = updateTransaction(1, TransactionCheckInput.DATE, "@1/0*/202#"),
        expectedResult = false
    )

    check(
        name = "when user enters only spaces in date",
        result = updateTransaction(1, TransactionCheckInput.DATE, "   "),
        expectedResult = false
    )

    check(
        name = "when user enters date with dash instead of slash",
        result = updateTransaction(1, TransactionCheckInput.DATE, "10-04-2025"),
        expectedResult = false
    )

    check(
        name = "when user enters letters instead of numbers",
        result = updateTransaction(1, TransactionCheckInput.DATE, "dd/MM/yyyy"),
        expectedResult = false
    )

    check(
        name = "when user enters date with month as word",
        result = updateTransaction(1, TransactionCheckInput.DATE, "10/April/2025"),
        expectedResult = false
    )

    check(
        name = "when user enters date with extra slashes",
        result = updateTransaction(1, TransactionCheckInput.DATE, "10//04//2025"),
        expectedResult = false
    )

    check(
        name = "when user enters date with missing year",
        result = updateTransaction(1, TransactionCheckInput.DATE, "10/04/"),
        expectedResult = false
    )

    check(
        name = "when user enters date with negative values",
        result = updateTransaction(1, TransactionCheckInput.DATE, "-10/-04/2025"),
        expectedResult = false
    )

    check(
        name = "when user enters date with zeros only",
        result = updateTransaction(1, TransactionCheckInput.DATE, "00/00/0000"),
        expectedResult = false
    )

    check(
        name = "when user enters future date beyond 2100",
        result = updateTransaction(1, TransactionCheckInput.DATE, "10/04/2500"),
        expectedResult = false
    )

    // endregion
}