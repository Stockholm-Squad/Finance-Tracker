package src

import src.model.Transaction
import src.model.TransactionCategory
import src.model.TransactionDate
import src.model.TransactionMonth

fun main() {
// region add
    /**
     * Checkers for amount parameter*/
    check(
        "when amount is less than 0 then return false",
        addTransaction(amount = "-1", day = "2", month = "2", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when amount is empty then return false",
        addTransaction(amount = "", day = "2", month = "2", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when amount contains characters then return false",
        addTransaction(amount = "A", day = "2", month = "2", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when amount contains special characters then return false",
        addTransaction(amount = "@", day = "2", month = "2", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when amount contains spaces then return false",
        addTransaction(amount = "    ", day = "2", month = "2", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when amount is equals 0 then return true",
        addTransaction(amount = "0", day = "2", month = "2", year = "2025", category = "1", type = "food"),
        true
    )
    check(
        "when amount is greater 0 then return true",
        addTransaction(amount = "2", day = "2", month = "2", year = "2025", category = "1", type = "food"),
        true
    )
    /**
     * Checkers for day parameter*/
    check(
        "when day equals 0 then return false",
        addTransaction(amount = "23", day = "0", month = "2", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when day is a negative number then return false",
        addTransaction(amount = "23", day = "-1", month = "02", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when day is greater than 31 then return false",
        addTransaction(amount = "23", day = "32", month = "2", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when day is empty then return false",
        addTransaction(amount = "23", day = "", month = "2", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when day contains spaces return false",
        addTransaction(amount = "23", day = "   ", month = "2", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when day contains characters return false",
        addTransaction(amount = "23", day = "sunday", month = "2", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when day contains special characters return false",
        addTransaction(amount = "23", day = "@", month = "2", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when day contains leading zeros then return false",
        addTransaction(amount = "03", day = "23", month = "2", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when day value less 32 and greater than 0 return true",
        addTransaction(amount = "23", day = "31", month = "2", year = "2025", category = "1", type = "food"),
        true
    )
    /**
     * Checkers for month parameter*/
    check(
        "when month equals 0 then return false",
        addTransaction(amount = "23", day = "23", month = "0", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when month is a negative number then return false",
        addTransaction(amount = "23", day = "23", month = "-1", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when month is greater than 12 then return false",
        addTransaction(amount = "23", day = "23", month = "13", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when day is empty then return false",
        addTransaction(amount = "23", day = "23", month = "", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when day contains spaces return false",
        addTransaction(amount = "23", day = "23", month = "    ", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when day contains characters return false",
        addTransaction(amount = "23", day = "23", month = "A", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when day contains special characters return false",
        addTransaction(amount = "23", day = "23", month = "@", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when month contains leading zeros then return false",
        addTransaction(amount = "23", day = "23", month = "02", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when day value less 13 and greater than 0 return true",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", category = "1", type = "food"),
        true
    )
    /**
     * Checkers for year parameter*/
    check(
        "when year equals 0 then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "0", category = "1", type = "food"),
        false
    )
    check(
        "when year is a negative number then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "0", category = "1", type = "food"),
        false
    )
    check(
        "when year is greater than current year then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2026", category = "1", type = "food"),
        false
    )
    check(
            "when year is less than current year then return false",
    addTransaction(amount = "23", day = "23", month = "2", year = "2024", category = "1", type = "food"),
    false
    )
    check(
        "when year is empty then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "", category = "1", type = "food"),
        false
    )
    check(
        "when year contains spaces return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "     ", category = "1", type = "food"),
        false
    )
    
    check(
        "when year contains characters return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "a", category = "1", type = "food"),
        false
    )
    check(
        "when year contains special characters return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "@", category = "1", type = "food"),
        false
    )
    check(
        "when year contains leading zeros then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "0200", category = "1", type = "food"),
        false
    )
    check(
        "when year value is current year year then return true",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", category = "1", type = "food"),
        true
    )
    /**
     * Checkers for category parameter*/
    check(
        "when category equals 0 then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", category = "0", type = "food"),
        false
    )
    check(
        "when category is a negative number then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", category = "-1", type = "food"),
        false
    )
    check(
        "when category is greater than 2 then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", category = "3", type = "food"),
        false
    )
    check(
        "when category is empty then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", category = "", type = "food"),
        false
    )
    check(
        "when category contains spaces return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", category = "   ", type = "food"),
        false
    )
    check(
        "when category contains characters return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", category = "A", type = "food"),
        false
    )
    check(
        "when year contains special characters return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", category = "@", type = "food"),
        false
    )
    check(
        "when year contains leading zeros then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", category = "01", type = "food"),
        false
    )
    check(
        "when year value less than 3 and greater than 0 then return true",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", category = "1", type = "food"),
        true
    )

    /**
     * Checkers for type parameter*/
    check(
        "when type contains numbers then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", category = "1", type = "2food"),
        false
    )
    check(
        "when type is empty then return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", category = "1", type = ""),
        false
    )
    check(
        "when type contains spaces return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", category = "1", type = "food"),
        false
    )
    check(
        "when type contains special characters return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", category = "1", type = "f@ood"),
        false
    )
    check(
        "when type characters less than 2 return false",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", category = "1", type = "fo"),
        false
    )
    check(
        "when type characters greater than 25 return false",
        addTransaction(
            amount = "23",
            day = "23",
            month = "2",
            year = "2025",
            category = "1",
            type = "foodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfood"
        ),
        false
    )
    check(
        "when type value is a valid value return true",
        addTransaction(amount = "23", day = "23", month = "2", year = "2025", category = "1", type = "food"),
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

fun addTransaction(amount: String, day: String, month: String, year: String, category: String, type: String): Boolean {

    return false
}