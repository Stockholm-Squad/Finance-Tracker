package src.storage

import src.model.Transaction
import src.model.TransactionDate
import src.model.TransactionMonth
import src.model.TransactionType
import src.storage.IFinancialTrackerStorage

class MemoryFinancialTrackerStorage : IFinancialTrackerStorage {
    companion object {
        private var allTransaction: MutableList<Transaction> = mutableListOf(Transaction(
            id = "1",
            amount = 1000.0,
            date = TransactionDate(1, TransactionMonth.FEBRUARY,2023),
            category = "Food",
            type = TransactionType.EXPANSES
        ),Transaction(
            id = "1",
            amount = 1000.0,
            date = TransactionDate(1, TransactionMonth.FEBRUARY,2023),
            category = "Food",
            type = TransactionType.EXPANSES
        ),Transaction(
            id = "2",
            amount = 1000.0,
            date = TransactionDate(1, TransactionMonth.JANUARY,2023),
            category = "Food",
            type = TransactionType.EXPANSES
        ),Transaction(
            id = "3",
            amount = 1000.0,
            date = TransactionDate(1, TransactionMonth.JANUARY,2023),
            category = "Food",
            type = TransactionType.EXPANSES
        ),
                (Transaction(
            id = " 4 ",
            amount = 1000.0,
            date = TransactionDate(1, TransactionMonth.JANUARY,2023),
            category = "Food",
            type = TransactionType.EXPANSES
        )))
    }
    override fun addTransaction(transaction: Transaction): Boolean {
        allTransaction.add(transaction)

        return true
    }

    override fun deleteTransactionById(transactionId: String): Boolean {
        return allTransaction.removeIf { it.id == transactionId }
    }

    override fun updateTransaction(transaction: Transaction): Boolean {
        val index = allTransaction.indexOfFirst { it.id == transaction.id }
        return if (index != -1) {
            allTransaction[index] = transaction
            true
        } else {
            false
        }
    }

    override fun getAllTransactions(): List<Transaction> {

        return allTransaction
    }

    override fun getTransactionById(transactionId: String): Transaction? {

        return allTransaction.find { it.id == transactionId }
    }

    override fun getTransactionsByMonth(transactionMonth: TransactionMonth): List<Transaction> {
        val result: MutableList<Transaction> = mutableListOf()
        allTransaction.forEach { transaction ->
            if (transaction.date.month == transactionMonth)
                result.add(transaction)
        }
        return result
    }
}