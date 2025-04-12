package src.update.validation

import src.model.Transaction
import src.storage.MemoryFinancialTrackerStorage

class UpdateTransactionActionValidator(): IUpdateTransactionActionValidator {

    private val memoryFinancialTrackerStorage: MemoryFinancialTrackerStorage = MemoryFinancialTrackerStorage()

    private val transactions: List<Transaction> = memoryFinancialTrackerStorage.getAllTransactions() ?: emptyList()

    override fun isValidateIndex(index: String): Boolean {
        val selectedIndex = index.trim()
        if (transactions.isEmpty()) {
            println("No transactions available.")
            return false
        }
        return selectedIndex.all { it.isDigit() }
                && selectedIndex.isNotEmpty()
                && selectedIndex.trim().toInt() in 1..transactions.size
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
                && category.trim().length in 2..25
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

    override fun isValidateDay(day: String): Boolean {
        val newDay = day.trim()
        return newDay.all { it.isDigit() }
                && newDay.isNotEmpty()
                && (newDay.length == 1 || newDay.length == 2)
                && newDay.toInt() in 1 .. 31
    }

    override fun isValidateMonth(month: String): Boolean {
        val newMonth = month.trim()
        return newMonth.all { it.isDigit() }
                && newMonth.isNotEmpty()
                && (newMonth.length == 1 || newMonth.length == 2)
                && newMonth.toInt() in 1 .. 12
    }

}