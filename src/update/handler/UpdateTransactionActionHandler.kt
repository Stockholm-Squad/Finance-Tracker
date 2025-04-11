package update.handler

import src.common.console.handler.ActionHandler
import src.model.*
import src.storage.IFinancialTrackerStorage
import src.update.parseDate
import src.update.validation.IUpdateTransactionActionValidator
import src.update.validation.UpdateTransactionActionValidator

class UpdateTransactionActionHandler : ActionHandler {

    private val updateTransactionActionValidator: IUpdateTransactionActionValidator = UpdateTransactionActionValidator()

    override fun handleAction(financialTrackerStorage: IFinancialTrackerStorage) {

        while (true) {
            val id = promptTransactionId() ?: continue
            val selectedTransaction = financialTrackerStorage.getTransactionById(id)

            if (selectedTransaction == null) {
                println("Transaction not found.")
                continue
            }

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
                    break
                }

                else -> println("Invalid choice.")
            }
        }
        return
    }

    private fun promptTransactionId(): Int? {
        println("Please enter id of transaction")
        val id = readlnOrNull()
        if (id == null || !updateTransactionActionValidator.isValidateId(id)) {
            println("Invalid input. Please enter a correct ID.")
            return null
        }
        return id.toInt()
    }

    private fun promptUpdateChoice(): Int? {
        println(
            "Please choose number of what do you need to update\n" +
                    "1. Category \n" +
                    "2. Type (Income - Expenses)\n" +
                    "3. Amount \n" +
                    "4. Date \n" +
                    "5. Exit"
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
        println("Please input new Category:")
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
            "1"-> selectedTransaction.copy(type = TransactionType.INCOME)
            "2"-> selectedTransaction.copy(type = TransactionType.EXPANSES)
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
        println("Please enter new Amount:")
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
        println("Please enter new Date (ex: 1/04/2025):")
        val dateInput = readlnOrNull()

        if (dateInput.isNullOrEmpty() || !updateTransactionActionValidator.isValidateDate(dateInput)) {
            println("Invalid date format.")
            return false
        }

        val (day, month, year) = dateInput.parseDate()
        val transactionDate = TransactionDate(day, TransactionMonth.entries[month - 1], year)

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

}