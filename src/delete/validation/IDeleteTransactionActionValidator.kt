package src.delete.validation

import src.model.Transaction
import src.storage.IFinancialTrackerStorage

interface IDeleteTransactionActionValidator {
    fun checkConfirmation():Boolean
    fun checkTransactionIndex(allTransaction: List<Transaction>, selectedIndex : String?):Boolean
    fun deleteTransaction(financialTrackerStorage: IFinancialTrackerStorage, id : String?) : Boolean
}