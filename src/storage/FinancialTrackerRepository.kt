package storage

import model.Transaction

class FinancialTrackerRepository(
    val financialTrackerStorage: IFinancialTrackerStorage
) : IFinancialTrackerRepository {
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
        TODO("Not yet implemented")
    }

    override fun getTransactionById(transactionId: Int): Transaction? {
        TODO("Not yet implemented")
    }

    override fun getTransactionsByMonth(month: Int): List<Transaction>? {
        TODO("Not yet implemented")
    }
}