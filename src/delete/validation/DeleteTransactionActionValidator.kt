package src.delete.validation

import src.model.Transaction

class DeleteTransactionActionValidator : IDeleteTransactionActionHandler {
    override fun checkIDisInt(id: String?): Boolean {
        return id?.let { it ->
            it.toIntOrNull()?.let {
                it >= 1
            }
        } ?: false
    }

    override fun checkConfirmation(): Boolean {
        println("Are you sure you want to delete this transaction? (y/n)")
        val input = readLine()
        input?.let {
            return it.toLowerCase() == "y"
        }
        return false
    }

    override fun checkIDinList(allTransaction: List<Transaction> , id : Int): Boolean {
        return allTransaction.any{ it.id == id }
    }

}