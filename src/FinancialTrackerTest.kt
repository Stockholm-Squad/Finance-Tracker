package src


fun main() {
    testCaseForDeleteTransaction()
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

    print("hello")
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

fun testCaseForDeleteTransaction() {

    fun deleteTransactionById(id: String): Boolean{
        return false
    }

    check(
        name = "delete existing transaction (ex:id = 2) is should return true ",
        result = deleteTransactionById("2"),
        expectedResult = true
    )


    check(
        name = "invalid delete non-existing transaction id = 999 it should return false",
        result = deleteTransactionById("999"),
        expectedResult = false
    )

    check(
        name = "invalid delete transaction with negative ID id = -1 it should return false",
        result = deleteTransactionById("-1"),
        expectedResult = false
    )

    check(
        name = "invalid delete transaction with id 0 it should return false",
        result = deleteTransactionById("0"),
        expectedResult = false
    )


    check(
        name = "delete transaction but have no transaction(first time that run app) it should return false",
        result = deleteTransactionById("1"),
        expectedResult = false
    )

    check(
        name = "enter invalid id ex:user input = 1a it should return false",
        result = deleteTransactionById("1a"),
        expectedResult = false
    )

    check(
        name = "enter invalid id ex:user input = 5 but 5 not exist it should return false",
        result = deleteTransactionById("5"),
        expectedResult = false
    )

}