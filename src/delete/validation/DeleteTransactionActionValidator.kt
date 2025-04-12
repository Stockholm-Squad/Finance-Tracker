package src.delete.validation

import src.model.Transaction
import src.storage.IFinancialTrackerStorage

class DeleteTransactionActionValidator : IDeleteTransactionActionValidator {

    override fun checkConfirmation(): Boolean {
        println(
            "------------------------------------------------------------------------ \n" +
                    "Are you sure you want to delete this transaction? (y/n) ❓"
        )
        val input = readLine()
        input?.let {
            return it.toLowerCase() == "y"
        }
        return false
    }

    override fun checkTransactionIndex(allTransaction: List<Transaction>, selectedIndex : String?): Boolean {
        return selectedIndex?.let { index ->
            index.toIntOrNull()?.let {
                if ("-" in index || index.toInt()==0){return false}
                if (index.toInt() - 1 >= allTransaction.size) {return false}
                val transactionId = allTransaction[index.toInt() - 1].id

                it >= 0 && allTransaction.any { it.id == transactionId }
            }
        } ?: false
    }

    override fun deleteTransaction(financialTrackerStorage: IFinancialTrackerStorage, id: String?) : Boolean {
        return financialTrackerStorage.deleteTransactionById(id!!)
    }


}