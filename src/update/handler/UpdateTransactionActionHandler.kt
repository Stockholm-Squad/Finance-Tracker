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
            val index = promptTransactionIndex(financialTrackerStorage)

            if (index == null) {
                println("Returning to main menu. 🔄 See you there!")
                break
            }

            val selectedTransaction = financialTrackerStorage.getAllTransactions()?.get(index - 1)
            if (selectedTransaction == null) {
                println("Oops! Transaction not found. 😕 Please check and try again.")
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
                        println("Exiting the update transaction")
                        return
                    }

                    else -> println("Invalid choice.")
                }

                println(
                    "------------------------------------------------------------------------ \n" +
                            "Do you want to update this transaction again? (y/n): ❓"
                )
                val continueChoice = readlnOrNull()?.trim()?.lowercase()
                if (continueChoice != "y") {
                    println("Finish Update.")
                    break
                }
            }
            println(
                "------------------------------------------------------------------------ \n" +
                        "Do you want to update another transaction? (y/n): ❓"
            )
            val anotherChoice = readlnOrNull()?.trim()?.lowercase()
            if (anotherChoice != "y") {
                println("Returning to main menu.")
                break
            }
        }
    }

    private fun promptTransactionIndex(financialTrackerStorage: IFinancialTrackerStorage): Int? {
        val transactions = financialTrackerStorage.getAllTransactions()

        if (transactions.isNullOrEmpty()) {
            println("No transactions found.")
            return null
        }

        println("Here are the available transactions:")
        transactions.forEachIndexed { index, transaction ->
            println("${index + 1}. Category: ${transaction.category}, Type: ${transaction.type} Amount: ${transaction.amount}, Date: ${transaction.date}")
        }

        while (true) {
            println("Please enter the index of the transaction (1 to ${transactions.size}):")
            val input = readlnOrNull()

            if (input == null || !updateTransactionActionValidator.isValidateIndex(input)) {
                println("Invalid input. Please enter a valid index.")
                continue
            }

            val selectedIndex = input.toInt()
            if (selectedIndex !in 1..transactions.size) {
                println("Oops! Please enter a valid index between 1 and ${transactions.size}. 😕 Try again.")
                continue
            }

            return selectedIndex
        }
    }

    private fun promptUpdateChoice(): Int? {
        println(
            "------------------------------------------------------------------------ \n" +
                    "Please choose number of what do you need to update\n" +
                    "1. Category \n" +
                    "2. Type (Income - Expenses)\n" +
                    "3. Amount \n" +
                    "4. Date \n" +
                    "5. Exit \n" +
                    "------------------------------------------------------------------------ "
        )
        val choice = readlnOrNull()
        if (choice == null || !updateTransactionActionValidator.isValidOption(choice)) {
            println("Oops! Please enter a number from 1 to 5. 😕 Try again.")
            return null
        }
        return choice.toInt()
    }

    private fun promptTransactionCategory(
        selectedTransaction: Transaction,
        financialTrackerStorage: IFinancialTrackerStorage
    ): Boolean {
        var newCategory: String?

        while (true) {
            println(
                "------------------------------------------------------------------------ \n" +
                        "Please input new Category ✍ : "
            )
            newCategory = readlnOrNull()

            if (!newCategory.isNullOrBlank() && updateTransactionActionValidator.isValidateCategory(newCategory)) {
                break
            }

            println("Category not acceptable. Please enter a value between 2 to 25 ❌")
        }

        return updateCategoryOfTransaction(newCategory!!, selectedTransaction, financialTrackerStorage)
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
            println("Oops! Couldn't update the category. 😕 Please try again.")
        }
        return categoryUpdatedSuccessfully
    }


    private fun promptUpdateTransactionType(
        selectedTransaction: Transaction,
        financialTrackerStorage: IFinancialTrackerStorage
    ): Boolean {
        var type: String?

        while (true) {
            println(
                "------------------------------------------------------------------------ \n" +
                        "Please choose number of what do you need \n" +
                        "1. Income \n" +
                        "2. Expenses \n" +
                        "------------------------------------------------------------------------ "
            )

            type = readlnOrNull()

            if (type != null && (type == "1" || type == "2")) {
                break
            }

            println("Oops! Please enter 1 for Income or 2 for Expenses. 😕 Try again.")
        }

        return updateTypeOfTransaction(type.toString(), selectedTransaction, financialTrackerStorage)
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
                println("Invalid type. Failed to update the Type ❌")
                return false
            }
        }

        val typeUpdatedSuccessfully = financialTrackerStorage.updateTransaction(updatedTransaction)
        if (typeUpdatedSuccessfully) {
            println("Type updated to: ${updatedTransaction.type}")
        } else {
            println("Oops! Couldn't update the type. 😕")
        }
        return typeUpdatedSuccessfully
    }

    private fun promptUpdateTransactionAmount(
        selectedTransaction: Transaction,
        financialTrackerStorage: IFinancialTrackerStorage
    ): Boolean {
        var newAmount: String?

        while (true) {
            println(
                "------------------------------------------------------------------------ \n" +
                        "Please enter new Amount:"
            )
            newAmount = readlnOrNull()

            if (newAmount != null && updateTransactionActionValidator.isValidateAmount(newAmount)) {
                break
            }

            println("Oops! The amount seems incorrect. 😕 Please enter a valid positive number.")
        }

        val amount = newAmount?.toDoubleOrNull()
        if (amount == null) {
            println("Oops! Please try again, couldn't update the amount 😕")
            return false
        }

        return updateTransactionAmount(amount, selectedTransaction, financialTrackerStorage)
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
            println("Oops! Couldn't update the amount. 😕 Please check and try again.")
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
            println("Oops! Couldn't update the date. 😕 Please try again.")
        }

        return success
    }


    private fun promptUpdateTransactionDay(): Int? {
        var isValid = false
        var day: Int? = null
        while (!isValid) {
            println(
                "------------------------------------------------------------------------ \n" +
                        "Please enter day:"
            )
            val input = readlnOrNull()

            if (input != null && updateTransactionActionValidator.isValidateDay(input)) {
                day = input.toInt()
                isValid = true
            } else {
                println("Oops! The day must be between 1 and 31. 😕 Please try again.")
            }
        }
        return day
    }

    private fun promptUpdateTransactionMonth(): Int? {
        var isValid = false
        var month: Int? = null
        while (!isValid) {
            println(
                "------------------------------------------------------------------------ \n" +
                        "Please enter month:"
            )
            val input = readlnOrNull()

            if (input != null && updateTransactionActionValidator.isValidateMonth(input)) {
                month = input.toInt()
                isValid = true
            } else {
                println("Oops! The month must be between 1 and 12. 😕 Please try again.")
            }
        }
        return month
    }

}