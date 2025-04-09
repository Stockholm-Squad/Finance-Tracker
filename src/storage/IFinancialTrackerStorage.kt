package src.storage

import src.model.Transaction
import src.model.TransactionUpdateType

interface IFinancialTrackerStorage {
    fun addTransaction(transaction: Transaction): Boolean
    fun deleteTransactionById(transactionId: Int): Boolean
    fun updateTransaction(transactionId: Int, type: TransactionUpdateType, newValue: String): Boolean
    fun getAllTransactions(): List<Transaction>?
    fun getTransactionById(transactionId: Int): Transaction?
    fun getTransactionsByMonth(month: Int): List<Transaction>?
}