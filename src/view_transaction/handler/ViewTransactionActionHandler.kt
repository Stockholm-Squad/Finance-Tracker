package src.view_transaction.handler

import src.common.console.handler.ActionHandler
import src.storage.IFinancialTrackerStorage
import src.view_transaction.validation.IViewTransactionActionValidator
import src.view_transaction.validation.ViewTransactionActionValidator

class ViewTransactionActionHandler : ActionHandler {
    private val viewTransactionActionValidator: IViewTransactionActionValidator = ViewTransactionActionValidator()
    override fun handleAction(financialTrackerRepository: IFinancialTrackerStorage) {
        // for printing the view
        val transactionsCount = financialTrackerRepository.getAllTransactions()?.size ?: 0
        if (transactionsCount != 0){
            while (true) {
                if (transactionsCount > 0) {
                    println("\nHere are all transactions :")
                    println("----------------------------------------------")
                    financialTrackerRepository.getAllTransactions()?.forEach(
                        {
                            println("${it.id}. ${it.category} ${it.amount} EGP ${it.date.day}/${it.date.month}/${it.date.year}")
                        }
                    )
                }

                println("\nWhich transaction would you like to view ? ")

                val choice = readlnOrNull()

                //region validation

                if (viewTransactionActionValidator.validateId(choice)) {
                    if (viewTransactionActionValidator.validateIdNotString(choice!!)) {
                        if (viewTransactionActionValidator.validateIdNotOutOfRange(
                                choice.toInt(),
                                transactionsSize = transactionsCount
                            )
                        ) {
                            val transaction = choice.toInt().let { financialTrackerRepository.getTransactionById(it) }
                            println("Transaction details is:")
                            println("----------------------------------------------")
                            println("Category / ${transaction?.category}\nAmount / ${transaction?.amount} EGP\nDate / 0${transaction?.date?.day}/${transaction?.date?.month}/${transaction?.date?.year}\nType / ${transaction?.type}\n")
                            println("Would you like to view more transaction you made? y/n")
                            val breakOrContinueChoice = readln()
                            if (breakOrContinueChoice == "n")
                                return
                        } else {
                            println("No transaction with that number 😒..Try Again")
                        }
                    } else {
                        println("Only numbers are valid 😡..Try Again")

                    }
                } else {
                    println("You didn't choose 😢..Try Again")
                }


            }
        }else{
            println("No transaction was found....add some transaction and come again\n")
        }

    }

}