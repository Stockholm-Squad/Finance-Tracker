package src.view_transaction.validation


class ViewTransactionActionValidator : IViewTransactionActionValidator {
    override fun validateIdNotString(id: String): Boolean {
        return try {
            id.toInt()
            true
        } catch (e: NumberFormatException) {
            println("Invalid Input, Please try again")
            false
        }
    }

    override fun validateIdNotOutOfRange(id: Int, transactionsSize: Int): Boolean {
        return if (id in 1..<transactionsSize) true else {
            println("Invalid Input, Please try again")
            false
        }
    }



}