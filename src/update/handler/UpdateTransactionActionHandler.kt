package update.handler

import src.common.console.handler.ActionHandler
import src.model.*
import src.storage.IFinancialTrackerStorage
import src.update.validation.IUpdateTransactionActionValidator
import src.update.validation.UpdateTransactionActionValidator

class UpdateTransactionActionHandler : ActionHandler {

    private val updateTransactionActionValidator: IUpdateTransactionActionValidator = UpdateTransactionActionValidator()

    override fun handleAction(financialTrackerStorage: IFinancialTrackerStorage) {
        while (true) {
            val index = promptTransactionIndex(financialTrackerStorage) ?: continue
            val selectedTransaction = financialTrackerStorage.getAllTransactions()?.get(index)

            if (selectedTransaction == null) {
                println("Transaction not found.")
                continue
            }

            while (true) {
                val choice = promptUpdateChoice() ?: continue
                val updateType = TransactionUpdateOption.entries.find { it.option == choice }

                when (updateType) {
                    TransactionUpdateOption.CATEGORY -> {
                        promptTransactionCategory(selectedTransaction, financialTrackerStorage)
                    }
                    TransactionUpdateOption.TYPE -> {
                        promptUpdateTransactionType(selectedTransaction, financialTrackerStorage)
                    }
                    TransactionUpdateOption.AMOUNT -> {
                        promptUpdateTransactionAmount(selectedTransaction, financialTrackerStorage)
                    }
                    TransactionUpdateOption.DATE -> {
                        promptUpdateTransactionDate(selectedTransaction, financialTrackerStorage)
                    }
                    TransactionUpdateOption.EXIT -> {
                        println("Exiting the program.")
                        return
                    }
                    else -> println("Invalid choice.")
                }

                println(
                    "----------------------------------------------------- \n" +
                    "Do you want to update this transaction again? (y/n):"
                )
                val continueChoice = readlnOrNull()?.trim()?.lowercase()
                if (continueChoice != "y") {
                    println("Finish Update.")
                    break
                }
            }
            break
        }
    }

    private fun promptTransactionIndex(financialTrackerStorage: IFinancialTrackerStorage): Int? {
        val transactions = financialTrackerStorage.getAllTransactions()  // Get all transactions

        if (transactions.isNullOrEmpty()) {
            println("No transactions found.")
            return null
        }

        println("Here are the available transactions:")
        transactions.forEachIndexed { index, transaction ->
            println("${index + 1}. Category: ${transaction.category}, Type: ${transaction.type} Amount: ${transaction.amount}, Date: ${transaction.date}")
        }

        println("Please enter the index of the transaction (1 to ${transactions.size}):")
        val input = readlnOrNull()

        if (input == null || !updateTransactionActionValidator.isValidateIndex(input)) {
            println("Invalid input. Please enter a valid index.")
            return null
        }

        val selectedIndex = input.toInt()

        if (selectedIndex !in 1..transactions.size) {
            println("Invalid input. Please enter a valid index between 1 and ${transactions.size}.")
            return null
        }

        return selectedIndex - 1 // Convert to zero-based index
    }

    private fun promptUpdateChoice(): Int? {
        println(
            "------------------------------------------------------ \n" +
                    "Please choose number of what do you need to update\n" +
                    "1. Category \n" +
                    "2. Type (Income - Expenses)\n" +
                    "3. Amount \n" +
                    "4. Date \n" +
                    "5. Exit \n" +
                    "-----------------------------------------------------"
        )
        val choice = readlnOrNull()
        if (choice == null || !updateTransactionActionValidator.isValidOption(choice)) {
            println("Invalid input. Please enter a number from 1 to 5.")
            return null
        }
        return choice.toInt()
    }

    private fun promptTransactionCategory(
        selectedTransaction: Transaction,
        financialTrackerStorage: IFinancialTrackerStorage
    ): Boolean {
        println(
            "----------------------------------------------------- \n" +
                    "Please input new Category:"
        )
        val newCategory = readlnOrNull()

        if (newCategory.isNullOrBlank() || !updateTransactionActionValidator.isValidateCategory(newCategory)) {
            println("Invalid category. It cannot be blank.")
            return false
        }

        return updateCategoryOfTransaction(newCategory, selectedTransaction, financialTrackerStorage)
    }

    private fun updateCategoryOfTransaction(
        newCategory: String,
        selectedTransaction: Transaction,
        financialTrackerStorage: IFinancialTrackerStorage
    ): Boolean {
        val updatedTransaction = selectedTransaction.copy(id = selectedTransaction.id, category = newCategory)
        val categoryUpdatedSuccessfully = financialTrackerStorage.updateTransaction(updatedTransaction)

        if (categoryUpdatedSuccessfully) {
            println("Category updated to: $newCategory")
        } else {
            println("Failed to update the category.")
        }
        return categoryUpdatedSuccessfully
    }


    private fun promptUpdateTransactionType(
        selectedTransaction: Transaction,
        financialTrackerStorage: IFinancialTrackerStorage
    ): Boolean {
        println(
            "----------------------------------------------------- \n" +
                    "Please choose number of what do you need \n" +
                    "1. Income \n" +
                    "2. Expenses \n" +
                    "----------------------------------------"
        )
        val type = readlnOrNull()
        if (type == null || !updateTransactionActionValidator.isValidateType(type)) {
            println("Invalid type. Must choose (1. Income or 2. Expenses).")
            return false
        }

        return updateTypeOfTransaction(type, selectedTransaction, financialTrackerStorage)
    }

    private fun updateTypeOfTransaction(
        newType: String,
        selectedTransaction: Transaction,
        financialTrackerStorage: IFinancialTrackerStorage
    ): Boolean {

        val updatedTransaction = when (newType) {
            "1" -> selectedTransaction.copy(type = TransactionType.INCOME)
            "2" -> selectedTransaction.copy(type = TransactionType.EXPANSES)
            else -> {
                println("Invalid type. Failed to update the Type.")
                return false
            }
        }

        val typeUpdatedSuccessfully = financialTrackerStorage.updateTransaction(updatedTransaction)
        if (typeUpdatedSuccessfully) {
            println("Type updated to: ${updatedTransaction.type}")
        } else {
            println("Failed to update the type.")
        }
        return typeUpdatedSuccessfully
    }

    private fun promptUpdateTransactionAmount(
        selectedTransaction: Transaction,
        financialTrackerStorage: IFinancialTrackerStorage
    ): Boolean {
        println(
            "----------------------------------------------------- \n" +
                    "Please enter new Amount:"
        )
        val newAmount = readlnOrNull()

        if (newAmount == null || !updateTransactionActionValidator.isValidateAmount(newAmount)) {
            println("Invalid amount. Must be a positive number.")
            return false
        }
        return updateTransactionAmount(newAmount.toDouble(), selectedTransaction, financialTrackerStorage)
    }

    private fun updateTransactionAmount(
        newAmount: Double,
        selectedTransaction: Transaction,
        financialTrackerStorage: IFinancialTrackerStorage
    ): Boolean {
        val updatedTransaction = selectedTransaction.copy(amount = newAmount)
        val success = financialTrackerStorage.updateTransaction(updatedTransaction)

        if (success) {
            println("Amount updated to: $newAmount")
        } else {
            println("Failed to update the amount.")
        }
        return success
    }

    private fun promptUpdateTransactionDate(
        selectedTransaction: Transaction,
        financialTrackerStorage: IFinancialTrackerStorage
    ): Boolean {

        val newDay = promptUpdateTransactionDay() ?: return false
        val newMonth = promptUpdateTransactionMonth() ?: return false
        val year = selectedTransaction.date.year

        val transactionDate = TransactionDate(
            day = newDay,
            month = TransactionMonth.entries[newMonth - 1],
            year = year
        )

        return updateTransactionDate(transactionDate, selectedTransaction, financialTrackerStorage)
    }

    private fun updateTransactionDate(
        newDate: TransactionDate,
        selectedTransaction: Transaction,
        financialTrackerStorage: IFinancialTrackerStorage
    ): Boolean {
        val updatedTransaction = selectedTransaction.copy(date = newDate)
        val success = financialTrackerStorage.updateTransaction(updatedTransaction)

        if (success) {
            println("Date updated to: $newDate")
        } else {
            println("Failed to update the date.")
        }

        return success
    }


    private fun promptUpdateTransactionDay(): Int? {
        var isValid = false
        var day: Int? = null
        while (!isValid) {
            println(
                "----------------------------------------------------- \n" +
                        "Please enter day:"
            )
            val input = readlnOrNull()

            if (input != null && updateTransactionActionValidator.isValidateDay(input)) {
                day = input.toInt()
                isValid = true
            } else {
                println("Invalid day value. Day must be between 1 and 31.")
            }
        }
        return day
    }

    private fun promptUpdateTransactionMonth(): Int? {
        var isValid = false
        var month: Int? = null
        while (!isValid) {
            println(
                "----------------------------------------------------- \n" +
                        "Please enter month:"
            )
            val input = readlnOrNull()

            if (input != null && updateTransactionActionValidator.isValidateMonth(input)) {
                month = input.toInt()
                isValid = true
            } else {
                println("Invalid month value. Month must be between 1 and 12.")
            }
        }
        return month
    }

}