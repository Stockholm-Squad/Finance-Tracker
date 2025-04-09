package src.console

import model.Transaction

import storage.IFinancialTrackerStorage

class FinancialTracker(val iFinancialTrackerStorage: IFinancialTrackerStorage) {
    fun getTransactionById(transactionId:Int): Transaction?{
        return null
    }

    fun getAllTransactions(): List<Transaction>? {
        return null
    }
}