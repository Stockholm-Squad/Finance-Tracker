package src.test.storage.file

import src.model.Transaction

interface IFileHelper {
    fun loadTransactions(): MutableList<Transaction>?
    fun saveTransactions(allTransactions: List<Transaction>?)
}