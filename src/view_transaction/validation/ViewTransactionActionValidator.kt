package src.view_transaction.validation


class ViewTransactionActionValidator : IViewTransactionActionValidator {
    override fun validateIdNotString(id: String): Boolean {
        return try {
            id.toInt()
            true
        } catch (e: NumberFormatException) {

            false
        }
    }

    override fun validateIdNotOutOfRange(id: Int, transactionsSize: Int): Boolean {
        return if (id in 1..transactionsSize) true
        else
            false

    }

    override fun validateId(id: String?): Boolean {
        return if (id != null) return true
        else false
    }

}