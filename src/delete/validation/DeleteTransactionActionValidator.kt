package src.delete.validation

import src.model.Transaction

class DeleteTransactionActionValidator : IDeleteTransactionActionHandler {

    override fun checkConfirmation(): Boolean {
        println("Are you sure you want to delete this transaction? (y/n)")
        val input = readLine()
        input?.let {
            return it.toLowerCase() == "y"
        }
        return false
    }

    override fun checkID(allTransaction: List<Transaction> , id : String?): Boolean {
        return id?.let { id ->
            id.toIntOrNull()?.let {
                it >= 1 && allTransaction.any{ it.id == id.toInt() }
            }
        } ?: false
    }

}