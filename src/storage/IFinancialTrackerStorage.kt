package storage

import model.Transaction

interface IFinancialTrackerStorage {
    fun addTransaction(transaction: Transaction): Boolean
    fun deleteTransactionById(transactionId: Int): Boolean
    fun updateTransaction(transaction: Transaction): Boolean
    fun getAllTransactions(): List<Transaction>?
    fun getTransactionById(transactionId: Int): Transaction?
    fun getTransactionsByMonth(month: Int): List<Transaction>?
}