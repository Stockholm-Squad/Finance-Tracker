package src.delete.handler

import src.common.console.handler.ActionHandler
import src.common.helper.display.IDisplayTransaction
import src.delete.validation.IDeleteTransactionActionValidator
import src.storage.IFinancialTrackerStorage

class DeleteTransactionActionHandler(private val validator : IDeleteTransactionActionValidator, private val helper: IDisplayTransaction) : ActionHandler {
    override fun handleAction(financialTrackerStorage: IFinancialTrackerStorage) {
        val allTransactions = financialTrackerStorage.getAllTransactions()
        if (!helper.displayAllTransactions()){
            println("No transactions to delete.")
            return
        }

        println("Enter the transaction to delete:")
        while(true){
            val input = readlnOrNull()
            val id = input?.trim()
            if(validator.checkTransactionIndex(allTransactions!!.toList() , id)){
                if(validator.checkConfirmation()){
                    if(validator.deleteTransaction(financialTrackerStorage , id)){
                        println("Transaction deleted successfully ✅")
                        break
                    } else {
                        println("Error deleting transaction ❌")
                        continue
                    }
                } else {
                    println("Action cancelled 🛑")
                    break
                }
            } else {
                println("Invalid input or not found ⚠️")
                break
            }
        }
    }

}