package src.test.storage.file

import src.model.Transaction
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class FileHelper(fileName: String) : IFileHelper {
    private val file = File(fileName)

    override fun loadTransactions(): MutableList<Transaction>? {
        if (!file.exists()) {
            return null
        }

        if (file.length() == 0L) {
            return mutableListOf()
        }

        return try {
            ObjectInputStream(file.inputStream()).use {
                it.readObject() as? MutableList<Transaction>
            }
        } catch (throwable: Throwable) {
            println("Error reading file: $throwable")
            null
        }
    }


    override fun saveTransactions(allTransactions: List<Transaction>?) {
        if (allTransactions == null) return
        println(allTransactions)
        ObjectOutputStream(file.outputStream()).use { output ->
            output.writeObject(allTransactions)
        }
    }
}