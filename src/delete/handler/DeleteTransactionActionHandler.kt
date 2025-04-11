package src.delete.handler

import src.common.console.handler.ActionHandler
import src.delete.validation.IDeleteTransactionActionHandler
import src.model.Transaction
import src.storage.IFinancialTrackerStorage

class DeleteTransactionActionHandler(val validator: IDeleteTransactionActionHandler) : ActionHandler {
    override fun handleAction(financialTrackerStorage: IFinancialTrackerStorage) {
        val allTransactions = financialTrackerStorage.getAllTransactions()
        if (!showAllTransactions(allTransactions)) return
        println("Enter the id of the transaction to delete:")
        while(true){
            val id = readlnOrNull()
            if(validator.checkID(allTransactions!!.toList() , id)){
                if(validator.checkConfirmation()){
                    if(financialTrackerStorage.deleteTransactionById(id!!.toInt())){
                        println("Transaction deleted successfully.")
                        break
                    }else{
                        println("error deleting transaction.")
                        continue
                    }
                }else{
                    println("Action cancelled.")
                    break
                }
            } else {
                println("Invalid input or not found.")
                continue
            }
        }
    }

    // for delete
    private fun showAllTransactions(allTransactions: List<Transaction>?) : Boolean {
        if (allTransactions?.isEmpty() == true) {
            println("No transactions to delete.")
            return false
        }

        allTransactions?.forEach{ it->
            println(
                "ID : ${it.id} \n" +
                "Amount : ${it.amount} \n" +
                "Date : ${it.date} \n" +
                "Category : ${it.category} \n" +
                "Type : ${it.type} \n" +
                "------------------\n"
            )
        }
        return true;
    }

}