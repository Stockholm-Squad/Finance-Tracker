package src.delete.handler

import src.common.console.handler.ActionHandler
import src.common.helper.display.IDisplayTransaction
import src.delete.validation.DeleteTransactionActionValidator
import src.delete.validation.IDeleteTransactionActionHandler
import src.model.Transaction
import src.storage.IFinancialTrackerStorage

class DeleteTransactionActionHandler(private val validator : IDeleteTransactionActionHandler,private val helper: IDisplayTransaction) : ActionHandler {
    override fun handleAction(financialTrackerStorage: IFinancialTrackerStorage) {
        val allTransactions = financialTrackerStorage.getAllTransactions()
        if (!helper.displayAllTransactions()) return

        println("Enter the id of the transaction to delete:")
        while(true){
            val id = readlnOrNull()
            if(validator.checkID(allTransactions!!.toList() , id)){
                if(validator.checkConfirmation()){
                    if(validator.deleteTransaction(financialTrackerStorage , id)){
                        println("Transaction deleted successfully.")
                        break
                    } else {
                        println("error deleting transaction.")
                        continue
                    }
                } else {
                    println("Action cancelled.")
                    break
                }
            } else {
                println("Invalid input or not found.")
                continue
            }
        }
    }

}