package src.delete

import src.delete.validation.DeleteTransactionActionValidator
import src.delete.validation.IDeleteTransactionActionValidator
import src.model.Transaction
import src.model.TransactionDate
import src.model.TransactionMonth
import src.model.TransactionType

fun main() {
    testCaseForDeleteTransaction()
}

fun testCaseForDeleteTransaction() {

    val transactions: MutableList<Transaction> = mutableListOf()
    // region delete
    fun deleteTransactionById(id: String): Boolean {
        val validator : IDeleteTransactionActionValidator = DeleteTransactionActionValidator()
        return validator.checkTransactionIndex(transactions , id)
    }

    src.check(
        name = "delete transaction but have no transaction(first time that run app) it should return false",
        result = deleteTransactionById("1"),
        expectedResult = false
    )

    transactions.add(Transaction(
        id = 1,
        amount = 1000.0,
        date = TransactionDate(1, TransactionMonth.APRIL,2023),
        category = "Food",
        type = TransactionType.EXPANSES
    ))



    src.check(
        name = "delete existing transaction (ex:id = 1) is should return true ",
        result = deleteTransactionById("1"),
        expectedResult = true
    )


    src.check(
        name = "invalid delete non-existing transaction id = 999 it should return false",
        result = deleteTransactionById("999"),
        expectedResult = false
    )

    src.check(
        name = "invalid delete transaction with negative ID id = -1 it should return false",
        result = deleteTransactionById("-1"),
        expectedResult = false
    )

    src.check(
        name = "invalid delete transaction with id 0 it should return false",
        result = deleteTransactionById("0"),
        expectedResult = false
    )

    src.check(
        name = "enter invalid id ex:user input = 1a it should return false",
        result = deleteTransactionById("1a"),
        expectedResult = false
    )

    src.check(
        name = "enter invalid id ex:user input = 5 but 5 not exist it should return false",
        result = deleteTransactionById("5"),
        expectedResult = false
    )
    // endregion

}
