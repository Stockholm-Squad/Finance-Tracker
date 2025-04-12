package src.test
import src.delete.validation.DeleteTransactionActionValidator
import src.delete.validation.IDeleteTransactionActionValidator
import src.model.Transaction
import src.model.TransactionDate
import src.model.TransactionMonth
import src.model.TransactionType

class DeleteTransactionTest : TransactionTest() {
    override fun runTest() {
        val transactions: MutableList<Transaction> = mutableListOf()
        // region delete
        fun deleteTransactionById(id: String): Boolean {
            val validator : IDeleteTransactionActionValidator = DeleteTransactionActionValidator()
            return validator.checkTransactionIndex(transactions , id)
        }
        println("\n<-----------------Delete Transactions Test-------------------->")

        check(
            name = "delete transaction but have no transaction(first time that run app) it should return false",
            result = deleteTransactionById("1"),
            expectedResult = false
        )

        transactions.add(
            Transaction(
            id = "2",
            amount = 1000.0,
            date = TransactionDate(1, TransactionMonth.APRIL,2023),
            category = "Food",
            type = TransactionType.EXPANSES
        )
        )



        check(
            name = "delete existing transaction (ex:index = 1) is should return true ",
            result = deleteTransactionById("1"),
            expectedResult = true
        )


        check(
            name = "invalid delete non-existing transaction id = 999 it should return false",
            result = deleteTransactionById("999"),
            expectedResult = false
        )

        check(
            name = "invalid delete transaction with negative ID id = -1 it should return false",
            result = deleteTransactionById("-1"),
            expectedResult = false
        )

        check(
            name = "invalid delete transaction with id 0 it should return false",
            result = deleteTransactionById("0"),
            expectedResult = false
        )

        check(
            name = "enter invalid id ex:user input = 1a it should return false",
            result = deleteTransactionById("1a"),
            expectedResult = false
        )

        check(
            name = "enter invalid id ex:user input = 5 but 5 not exist it should return false",
            result = deleteTransactionById("5"),
            expectedResult = false
        )
        check(
            name = "when user enter letter it should return false",
            result = deleteTransactionById("abcABC"),
            expectedResult = false
        )
        check(
            name = "when user enter special Char it should return false",
            result = deleteTransactionById("1@"),
            expectedResult = false
        )

        println("<------------------------End of Delete Transaction Test---------------------------->")
        // endregion
    }



}
