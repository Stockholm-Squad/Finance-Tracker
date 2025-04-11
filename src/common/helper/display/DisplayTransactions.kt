package src.common.helper.display

import src.model.Transaction

class DisplayTransactions(private val allTransaction: List<Transaction>) : IDisplayTransaction {
    override fun displayAllTransactions() : Boolean {
        if (allTransaction.isEmpty()) {
            println("No transactions to delete.")
            return false
        }

        allTransaction.forEach{ it->
            println(
                "ID : ${it.id} \n" +
                "Amount : ${it.amount} \n" +
                "Date : ${it.date} \n" +
                "Category : ${it.category} \n" +
                "Type : ${it.type} \n" +
                "------------------\n"
            )
        }
        return true
    }
}