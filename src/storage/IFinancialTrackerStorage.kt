package src.storage

import src.model.Transaction
import src.model.TransactionUpdateType

interface IFinancialTrackerStorage {
    fun addTransaction(transaction: Transaction): Boolean
    fun deleteTransactionById(transactionId: Int): Boolean
    fun updateTransaction(transactionId: String, type: TransactionUpdateType, newValue: String): Boolean
    fun getAllTransactions(): List<Transaction>?
    fun getTransactionById(transactionId: String): Transaction?
    fun getTransactionsByMonth(month: Int): List<Transaction>?
}