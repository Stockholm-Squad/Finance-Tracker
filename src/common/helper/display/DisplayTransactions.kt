package src.common.helper.display

import src.model.Transaction

class DisplayTransactions(private val allTransaction: List<Transaction>) : IDisplayTransaction {
    override fun displayAllTransactions() : Boolean {
        if (allTransaction.isEmpty()) {
            return false
        }

        allTransaction.forEachIndexed(){index, it->
            println(
                ". ${index+1} \n" +
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