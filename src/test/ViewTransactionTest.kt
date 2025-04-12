package src.test

import src.model.Transaction
import src.model.TransactionDate
import src.model.TransactionMonth
import src.model.TransactionType
import src.storage.IFinancialTrackerStorage
import src.storage.MemoryFinancialTrackerStorage
import src.view_transaction.validation.IViewTransactionActionValidator
import src.view_transaction.validation.ViewTransactionActionValidator

class ViewTransactionTest : TransactionTest() {

    override fun runTest() {

        println("<------------------------ View Transaction Test ---------------------------->")

        check(
            name = "when user send valid id, return true",
            result = getTransactionById("2"),
            expectedResult = true
        )
        check(
            name = "when user send out of range id, return false",
            result = getTransactionById("100"),
            expectedResult = false
        )

        check(
            name = "when user send out of range and negative id, return false",
            result = getTransactionById("-1"),
            expectedResult = false
        )
        check(
            name = "when user send zero, return false",
            result = getTransactionById("0"),
            expectedResult = false
        )
        check(
            name = "when user send enter wrong input, return false",
            result = getTransactionById("a"),
            expectedResult = false
        )

        check(
            name = "when user send enter 0 before choice, return false",
            result = getTransactionById("01"),
            expectedResult = false
        )
        check(
            name = "when user send enter empty input, return false",
            result = getTransactionById(""),
            expectedResult = false
        )


        // get all transaction

        val listSize = getAllTransaction().size
        check(
            name = "when transaction list has data, return true",
            result = listSize != 0,
            expectedResult = true
        )
        check(
            name = "when list in init but has no data, return false", result = listSize == 0, expectedResult = false
        )


        // end region for view transaction
        println("<------------------------ End of View Transaction Test ---------------------------->")
    }

    private fun getTransactionById(id: String): Boolean {
        val viewTransactionActionValidator: IViewTransactionActionValidator = ViewTransactionActionValidator()

        return viewTransactionActionValidator.validateId(id)
                && viewTransactionActionValidator.validateIdNotString(id)
                && viewTransactionActionValidator.validateIdNotOutOfRange(
            id.toInt(),
            getAllTransaction().size
        )
    }

    private fun getAllTransaction(): List<Transaction> {

        return MemoryFinancialTrackerStorage().getAllTransactions()
    }
}