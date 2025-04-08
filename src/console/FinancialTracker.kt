package src.console

import src.model.Transaction
import src.storage.IFinancialTrackerStorage

class FinancialTracker(val financialTrackerStorage: IFinancialTrackerStorage) {
    fun getTransactionByMonth(month: String): List<Transaction>? {
        return null
    }
}