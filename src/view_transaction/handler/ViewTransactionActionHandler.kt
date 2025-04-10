package src.view_transaction.handler

import src.common.console.handler.ActionHandler
import src.storage.IFinancialTrackerStorage
import src.view_transaction.validation.IViewTransactionActionValidator
import src.view_transaction.validation.ViewTransactionActionValidator

class ViewTransactionActionHandler : ActionHandler {
    private val viewTransactionActionValidator: IViewTransactionActionValidator = ViewTransactionActionValidator()
    override fun handleAction(financialTrackerRepository: IFinancialTrackerStorage) {
        // for printing the view
        while (true) {
            val transactionsCount = financialTrackerRepository.getAllTransactions()?.size ?:0
            if (transactionsCount > 0) {
                println("\nHere are all transactions :")
            financialTrackerRepository.getAllTransactions()?.forEach(
                {
                    println("${it.id}. ${it.category} ${it.amount} EGP ${it.date.day}/${it.date.month}/${it.date.year}")
                }
            )
            }

            println("\nWhich transaction would you like to view ? ")
            val choice = readlnOrNull()

            //region validation

              if (choice != null) {
                 if ( viewTransactionActionValidator.validateIdNotString(choice) )
                 if ( viewTransactionActionValidator.validateIdNotOutOfRange(choice.toInt(),transactionsCount)) {
                    val transaction = financialTrackerRepository.getTransactionById(choice.toInt())
                    println("\nTransaction details is:\nCategory / ${transaction?.category}\nAmount / ${transaction?.amount} EGP\nDate / 0${transaction?.date?.day}/${transaction?.date?.month}/${transaction?.date?.year}\nType / ${transaction?.type}\n")
                    return
                }
              }
            else println("Invalid Input, Please try again")

            //end


        }
    }

}