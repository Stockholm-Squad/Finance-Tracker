package src.storage

import src.model.Transaction
import src.model.TransactionCheckInput
import java.util.Collections.emptyList

class MemoryFinancialTrackerStorage : IFinancialTrackerStorage {

    override fun addTransaction(transaction: Transaction): Boolean {
        return true
    }

    override fun deleteTransactionById(transactionId: Int): Boolean {
        return true
    }

    override fun updateTransaction(transactionId: Int, field: TransactionCheckInput, newValue: String): Boolean {
        return true
    }

    override fun getAllTransactions(): List<Transaction>? {
        return emptyList()
    }

    override fun getTransactionById(transactionId: Int): Transaction? {
        return null
    }

    override fun getTransactionsByMonth(month: Int): List<Transaction>? {
        return emptyList()
    }
}
