package src.storage

import src.model.Transaction
import src.model.TransactionDate
import src.model.TransactionMonth
import src.model.TransactionType
import src.storage.IFinancialTrackerStorage

class MemoryFinancialTrackerStorage : IFinancialTrackerStorage {
    val allTransaction: MutableList<Transaction> = mutableListOf()

    init {
        allTransaction.add(
            Transaction(
                id = 1,
                amount = 1.2,
                date = TransactionDate(day = 2, month = TransactionMonth.MAY, year = 2002),
                type = TransactionType.INCOME,
                category = "Food"
            )
        )
        allTransaction.add(
            Transaction(
                id = 2,
                amount = 1.2,
                date = TransactionDate(day = 2, month = TransactionMonth.MAY, year = 2002),
                type = TransactionType.INCOME,
                category = "NBE"
            )
        )
        allTransaction.add(
            Transaction(
                id = 3,
                amount = 1.2,
                date = TransactionDate(day = 2, month = TransactionMonth.MAY, year = 2002),
                type = TransactionType.INCOME,
                category = "NBE"
            )
        )
    }

    override fun addTransaction(transaction: Transaction): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteTransactionById(transactionId: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun updateTransaction(transaction: Transaction): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAllTransactions(): List<Transaction> {

        return allTransaction
    }

    override fun getTransactionById(transactionId: Int): Transaction? {

        return allTransaction.find { it.id == transactionId }
    }

    override fun getTransactionsByMonth(month: Int): List<Transaction>? {
        return null
    }
}