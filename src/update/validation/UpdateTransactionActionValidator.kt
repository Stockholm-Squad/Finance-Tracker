package src.update.validation

import src.getAllTransaction
import src.update.parseDate

class UpdateTransactionActionValidator : IUpdateTransactionActionValidator {

    private val transactionsSize: Int = getAllTransaction()?.size ?: 0

    override fun isValidateId(id: String): Boolean {
        // still have point not cover need size of list transactions
        val selectedId = id.trim()
        return selectedId.all { it.isDigit() }
                && selectedId.isNotEmpty()
                && selectedId.toInt() in 1 .. transactionsSize
    }

    override fun isValidOption(option: String): Boolean {
        val selectedOption = option.trim()
        return selectedOption.all { it.isDigit() }
                && selectedOption.isNotEmpty()
                && selectedOption.length == 1
                && selectedOption.toInt() in 1..5
    }

    override fun isValidateCategory(category: String): Boolean {
        return category.trim().isNotEmpty()
    }

    override fun isValidateType(type: String): Boolean {
        val selectedType = type.trim()
        return selectedType.all { it.isDigit() }
                && selectedType.isNotEmpty()
                && selectedType.length == 1
                && selectedType.toInt() in 1..2
    }

    override fun isValidateAmount(amount: String): Boolean {
        val formattedAmount = amount.trim().replace(".","")
        return formattedAmount.all { it.isDigit() }
                && formattedAmount.isNotEmpty()
                && formattedAmount.toDouble() > 0
    }

    // Validates the date string
    override fun isValidateDate(date: String): Boolean {
        try {
            // Parse date string into day, month, year
            val (day, month, year) = date.parseDate()

            // Check if the day, month, and year are within valid ranges
            if (day !in 1..31 || month !in 1..12 || year !in 1000..2100) return false

            // Check number of days in the month (handling leap years for February)
            val daysInMonth = when (month) {
                4, 6, 9, 11 -> 30  // April, June, September, November
                2 -> if (isLeapYear(year)) 29 else 28  // February
                else -> 31  // All other months
            }

            return day <= daysInMonth
        } catch (e: Exception) {
            // Return false if parsing fails or any validation fails
            return false
        }
    }
    // Checks if the year is a leap year
    private fun isLeapYear(year: Int): Boolean {
        // Leap year check: divisible by 4 but not 100, unless divisible by 400
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
    }
}