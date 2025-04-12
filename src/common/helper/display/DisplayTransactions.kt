package src.common.helper.display

import src.model.Transaction

class DisplayTransactions(private val allTransaction: List<Transaction>) : IDisplayTransaction {
    override fun displayAllTransactions(): Boolean {
        if (allTransaction.isEmpty()) {
            return false
        }

        allTransaction.forEachIndexed() { index, it ->
            println(
                "${index + 1}. Category: ${it.category}, " +
                        "Type: ${it.type} Amount: ${it.amount}, Date: ${it.date}"
            )
        }
        return true
    }
}