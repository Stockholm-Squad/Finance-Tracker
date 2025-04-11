package src.storage

import src.model.Transaction
import src.storage.IFinancialTrackerStorage

class MemoryFinancialTrackerStorage : IFinancialTrackerStorage {
    val allTransaction: MutableList<Transaction> = mutableListOf()

    override fun addTransaction(transaction: Transaction): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteTransactionById(transactionId: Int): Boolean {
        return allTransaction.removeIf { it.id == transactionId }
    }

    override fun updateTransaction(transaction: Transaction): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAllTransactions(): List<Transaction>? {

        return allTransaction
    }

    override fun getTransactionById(transactionId: Int): Transaction? {


        return allTransaction.find { it.id == transactionId}
    }

    override fun getTransactionsByMonth(month: Int): List<Transaction>? {
        return null
    }
}