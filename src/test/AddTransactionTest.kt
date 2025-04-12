package src.test

import src.add.validation.AddTransactionActionValidator

class AddTransactionTest : TransactionTest() {

    override fun runTest() {
        /**
         * Checkers for amount parameter*/
        println("<------------------------Add Transaction Test---------------------------->")
        println("<------------------------Checkers for amount parameter---------------------------->")
        check(
            "when amount is less than 0 then return false",
            addTransaction(amount = "-1", day = "2", month = "2", type = "1", category = "food"),
            false
        )
        check(
            "when amount is empty then return false",
            addTransaction(amount = "", day = "2", month = "2", type = "1", category = "food"),
            false
        )
        check(
            "when amount contains characters then return false",
            addTransaction(amount = "A", day = "2", month = "2", type = "1", category = "food"),
            false
        )
        check(
            "when amount contains special characters then return false",
            addTransaction(amount = "@", day = "2", month = "2", type = "1", category = "food"),
            false
        )
        check(
            "when amount is equals 0 then return false",
            addTransaction(amount = "0", day = "2", month = "2", type = "1", category = "food"),
            false
        )
        check(
            "when amount is greater 0 then return true",
            addTransaction(amount = "2", day = "2", month = "2", type = "1", category = "food"),
            true
        )
        /**
         * Checkers for day parameter*/
        println("<------------------------Checkers for day parameter---------------------------->")
        check(
            "when day equals 0 then return false",
            addTransaction(amount = "23", day = "0", month = "2", type = "1", category = "food"),
            false
        )
        check(
            "when day is a negative number then return false",
            addTransaction(amount = "23", day = "-1", month = "02", type = "1", category = "food"),
            false
        )
        check(
            "when day is greater than 31 then return false",
            addTransaction(amount = "23", day = "32", month = "2", type = "1", category = "food"),
            false
        )
        check(
            "when day is empty then return false",
            addTransaction(amount = "23", day = "", month = "2", type = "1", category = "food"),
            false
        )
        check(
            "when day contains characters return false",
            addTransaction(amount = "23", day = "sunday", month = "2", type = "1", category = "food"),
            false
        )
        check(
            "when day contains leading zeros then return false",
            addTransaction(amount = "3", day = "03", month = "2", type = "1", category = "food"),
            false
        )
        check(
            "when day value less 32 and greater than 0 return true",
            addTransaction(amount = "2", day = "31", month = "2", type = "1", category = "food"),
            true
        )
        /**
         * Checkers for month parameter*/
        println("<------------------------Checkers for month parameter---------------------------->")
        check(
            "when month equals 0 then return false",
            addTransaction(amount = "23", day = "23", month = "0", type = "1", category = "food"),
            false
        )
        check(
            "when month is a negative number then return false",
            addTransaction(amount = "23", day = "23", month = "-1", type = "1", category = "food"),
            false
        )
        check(
            "when month is greater than 12 then return false",
            addTransaction(amount = "23", day = "23", month = "13", type = "1", category = "food"),
            false
        )
        check(
            "when month is empty then return false",
            addTransaction(amount = "23", day = "23", month = "", type = "1", category = "food"),
            false
        )
        check(
            "when month contains characters return false",
            addTransaction(amount = "23", day = "23", month = "A", type = "1", category = "food"),
            false
        )
        check(
            "when month contains leading zeros then return false",
            addTransaction(amount = "23", day = "23", month = "02", type = "1", category = "food"),
            false
        )
        check(
            "when month value less 13 and greater than 0 return true",
            addTransaction(amount = "2", day = "31", month = "2", type = "1", category = "food"),
            true
        )
        /**
         * Checkers for type parameter*/
        println("<------------------------Checkers for type parameter---------------------------->")
        check(
            "when type equals 0 then return false",
            addTransaction(amount = "23", day = "23", month = "2", type = "0", category = "food"),
            false
        )
        check(
            "when type is a negative number then return false",
            addTransaction(amount = "23", day = "23", month = "2", type = "-1", category = "food"),
            false
        )
        check(
            "when type is greater than 2 then return false",
            addTransaction(amount = "23", day = "23", month = "2", type = "3", category = "food"),
            false
        )
        check(
            "when type is empty then return false",
            addTransaction(amount = "23", day = "23", month = "2", type = "", category = "food"),
            false
        )
        check(
            "when type contains characters return false",
            addTransaction(amount = "23", day = "23", month = "2", type = "A", category = "food"),
            false
        )
        /**
         * Checkers for category parameter*/
        println("<------------------------Checkers for category parameter---------------------------->")
        check(
            "when category is empty then return false",
            addTransaction(amount = "23", day = "23", month = "2", type = "1", category = ""),
            false
        )
        check(
            "when category characters less than 2 return false",
            addTransaction(amount = "23", day = "23", month = "2", type = "1", category = "fo"),
            false
        )
        check(
            "when category characters greater than 25 return false",
            addTransaction(
                amount = "23",
                day = "23",
                month = "2",

                type = "1",
                category = "foodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfoodfood"
            ),
            false
        )
        check(
            "when category value is a valid value return true",
            addTransaction(amount = "23", day = "23", month = "2", type = "1", category = "food"),
            true
        )
        println("<------------------------End of Add Transaction test---------------------------->")
    }


    private fun addTransaction(amount: String, day: String, month: String, type: String, category: String): Boolean {
        if (!AddTransactionActionValidator().validateDay(day)) {
//            println("day is not valid")
            return false
        }
        if (!AddTransactionActionValidator().validateAmount(amount)) {
//            println("amount is not valid")
            return false
        }
        if (!AddTransactionActionValidator().validateMonth(month)) {
//            println("month is not valid")
            return false
        }
        if (!AddTransactionActionValidator().validateType(type)) {
//            println("type is not valid")
            return false
        }
        if (!AddTransactionActionValidator().validateCategory(category)) {
//            println("category is not valid")
            return false
        }
        return true
    }
}