package src.test.storage.file

import src.model.Transaction
import src.model.TransactionMonth
import src.storage.IFinancialTrackerStorage

class FileFinancialTrackerStorage(
    private val fileHelper: IFileHelper
) : IFinancialTrackerStorage {
    companion object {
        val allTransaction: MutableList<Transaction>? = mutableListOf()
    }

    init {
        val allTransactionFromFile = fileHelper.loadTransactions()
        if (allTransactionFromFile != null) {
            allTransaction?.addAll(allTransactionFromFile)
        }
    }

    override fun addTransaction(transaction: Transaction): Boolean {
        println(transaction)
        try{
            allTransaction?.add(transaction)
        }catch (ex:Exception){
            println(ex.message)
        }
        try {
            fileHelper.saveTransactions(allTransaction?.toList())
            return true
        } catch (throwable: Throwable) {
            return false
        }
    }

    override fun deleteTransactionById(transactionId: String): Boolean {
        val transactionRemoved = allTransaction?.removeIf { it.id == transactionId } ?: false
        if (!transactionRemoved) return false
        try {
            fileHelper.saveTransactions(allTransaction?.toList())
            return true
        } catch (throwable: Throwable) {
            println(throwable.message)
            return false
        }
    }

    override fun updateTransaction(transaction: Transaction): Boolean {
        val index = allTransaction?.indexOfFirst { it.id == transaction.id } ?: return false
        if (index == -1) return false

        allTransaction?.set(index, transaction) ?: return false
        try {
            fileHelper.saveTransactions(allTransaction?.toList())
            return true
        } catch (throwable: Throwable) {
            return false
        }
    }

    override fun getAllTransactions(): List<Transaction> {
        return fileHelper.loadTransactions() ?: listOf()
    }

    override fun getTransactionById(transactionId: String): Transaction? {
        return allTransaction?.find { it.id == transactionId }
    }

    override fun getTransactionsByMonth(transactionMonth: TransactionMonth): List<Transaction> {
        val result: MutableList<Transaction> = mutableListOf()
        allTransaction?.forEach { transaction ->
            if (transaction.date.month == transactionMonth)
                result.add(transaction)
        }
        return result
    }

}