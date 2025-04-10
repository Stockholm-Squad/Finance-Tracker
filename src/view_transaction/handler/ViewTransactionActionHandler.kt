package src.view_transaction.handler

import src.common.console.handler.ActionHandler
import src.storage.IFinancialTrackerStorage
import src.view_transaction.validation.IViewTransactionActionValidator
import src.view_transaction.validation.ViewTransactionActionValidator

class ViewTransactionActionHandler : ActionHandler {
    val ViewTransactionActionValidator: IViewTransactionActionValidator = ViewTransactionActionValidator()
    override fun handleAction(financialTrackerRepository: IFinancialTrackerStorage) {
        // for printing the view
        while (true) {

            financialTrackerRepository.getAllTransactions()?.forEach(
                {

                    println("${it.id}. ${it.category} ${it.amount} EGP ${it.date.day}/${it.date.month}/${it.date.year}")
                }
            )

            println("\nWhich transaction would you like to view")
            val choice = readlnOrNull()

            //region validation

            //end

            val transaction = financialTrackerRepository.getTransactionById(choice!!.toInt())

            println("\nTransaction details is:\nCategory / ${transaction?.category}\nAmount / ${transaction?.amount} EGP\nDate / 0${transaction?.date?.day}/${transaction?.date?.month}/${transaction?.date?.year}\nType / ${transaction?.type}\n")
            return
        }
    }

}