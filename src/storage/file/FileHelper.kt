package src.test.storage.file

import src.model.Transaction
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class FileHelper(fileName: String) : IFileHelper {
    private val file = File(fileName)

    override fun loadTransactions(): MutableList<Transaction>? {
        if (!file.exists()) return null
        return try {
            ObjectInputStream(file.inputStream()).use {
                it.readObject() as MutableList<Transaction>?
            }
        } catch (throwable: Throwable) {
            null
        }
    }

    override fun saveTransactions(allTransactions: List<Transaction>?) {
        if (allTransactions == null) return
        ObjectOutputStream(file.outputStream()).use { output ->
            output.writeObject(allTransactions)
        }
    }
}