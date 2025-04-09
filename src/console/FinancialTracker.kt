package src.console

import src.model.Transaction
import src.storage.IFinancialTrackerStorage

class FinancialTracker(val iFinancialTrackerStorage: IFinancialTrackerStorage) {
    fun getTransactionById(transactionId:Int):Transaction?{
        return null
    }

    fun getAllTransactions(): List<Transaction>? {
        return null
    }
}