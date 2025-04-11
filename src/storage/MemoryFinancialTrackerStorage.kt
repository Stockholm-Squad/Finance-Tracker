package src.storage

import src.model.Transaction
import src.model.TransactionMonth

class MemoryFinancialTrackerStorage : IFinancialTrackerStorage {
    val allTransaction: MutableList<Transaction> = mutableListOf()

    override fun addTransaction(transaction: Transaction): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteTransactionById(transactionId: Int): Boolean {
        TODO("Not yet implemented")
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

    override fun getTransactionsByMonth(transactionMonth: TransactionMonth): List<Transaction>? {
        val result: MutableList<Transaction>? = null
        allTransaction.forEach{ transaction ->
            if (transaction.date.month == transactionMonth)
            result?.add(transaction)
        }
        return result
    }
}