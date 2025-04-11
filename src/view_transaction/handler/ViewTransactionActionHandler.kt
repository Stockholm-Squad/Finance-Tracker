package src.view_transaction.handler

import src.common.console.handler.ActionHandler
import src.storage.IFinancialTrackerStorage
import src.view_transaction.validation.IViewTransactionActionValidator
import src.view_transaction.validation.ViewTransactionActionValidator

class ViewTransactionActionHandler : ActionHandler {
    private val viewTransactionActionValidator: IViewTransactionActionValidator = ViewTransactionActionValidator()
    override fun handleAction(financialTrackerStorage: IFinancialTrackerStorage) {
        val transactions = financialTrackerStorage.getAllTransactions()
        if (transactions.isNullOrEmpty()) {
            println("No transaction was found....add some transaction and come again\n")
            return
        }

        while (true) {
            println("\nHere are all transactions:")
            println("----------------------------------------------")
            transactions.forEachIndexed { index, transaction ->
                println("${index + 1}. ${transaction.category} ${transaction.amount} EGP ${transaction.date.day}/${transaction.date.month}/${transaction.date.year}")
            }

            println("\nWhich transaction would you like to view? (or 'q' to quit)")
            val choice = readlnOrNull() ?: continue


            when {
                !viewTransactionActionValidator.validateId(choice) -> {
                    println("You didn't choose 😢..Try Again")
                    continue
                }

                !viewTransactionActionValidator.validateIdNotString(choice) -> {
                    println("Only numbers are valid 😡..Try Again")
                    continue
                }

                !viewTransactionActionValidator.validateIdNotOutOfRange(choice.toInt(), transactions.size) -> {
                    println("No transaction with that number 😒..Try Again")
                    continue
                }
            }


            val transaction = financialTrackerStorage.getTransactionById(choice.toInt()) ?: continue
            println("Transaction details is:")
            println("----------------------------------------------")
            println("Category / ${transaction.category}\nAmount / ${transaction.amount} EGP\nDate / 0${transaction.date.day}/${transaction.date.month}/${transaction.date?.year}\nType / ${transaction.type}\n")

            println("\nWould you like to view more transactions? (y/n)")
            val breakOrContinueChoice = readlnOrNull()
            if (breakOrContinueChoice == "n")
                return
        }
    }

}