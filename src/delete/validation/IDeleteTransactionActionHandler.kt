package src.delete.validation

import src.model.Transaction
import src.storage.IFinancialTrackerStorage

interface IDeleteTransactionActionHandler {
    fun checkConfirmation():Boolean
    fun checkID(allTransaction: List<Transaction>, id : String?):Boolean
    fun deleteTransaction(financialTrackerStorage: IFinancialTrackerStorage, id : String?) : Boolean
}