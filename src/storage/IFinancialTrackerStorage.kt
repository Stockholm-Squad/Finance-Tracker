package src.storage

import src.model.Transaction
import src.model.TransactionMonth

interface IFinancialTrackerStorage {
    fun addTransaction(transaction: Transaction): Boolean
    fun deleteTransactionById(transactionId: Int): Boolean
    fun updateTransaction(transaction: Transaction): Boolean
    fun getAllTransactions(): List<Transaction>?
    fun getTransactionById(transactionId: Int): Transaction?
    fun getTransactionsByMonth(transactionMonth: TransactionMonth): List<Transaction>?
}