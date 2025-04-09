package src.storage

import src.model.Transaction
import src.model.TransactionUpdateType
import java.util.Collections.emptyList

class MemoryFinancialTrackerStorage : IFinancialTrackerStorage {

    override fun addTransaction(transaction: Transaction): Boolean {
        return false
    }

    override fun deleteTransactionById(transactionId: Int): Boolean {
        return false
    }

    override fun updateTransaction(transactionId: String, type: TransactionUpdateType, newValue: String): Boolean {
        return false
    }

    override fun getAllTransactions(): List<Transaction>? {
        return emptyList()
    }

    override fun getTransactionById(transactionId: String): Transaction? {
        return null
    }

    override fun getTransactionsByMonth(month: Int): List<Transaction>? {
        return emptyList()
    }
}
