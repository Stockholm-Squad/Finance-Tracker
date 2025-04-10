package src.report.handler

import src.common.console.handler.ActionHandler
import src.model.Transaction
import src.report.validation.TransactionReportActionValidator
import src.storage.IFinancialTrackerStorage

class TransactionReportActionHandler : ActionHandler {
    override fun handleAction(financialTrackerStorage: IFinancialTrackerStorage) {
        println("Please Enter Month to view Monthly Report : ")
        val month = readlnOrNull()
        val isValidMonth = TransactionReportActionValidator().isMonthValid(month)
        if (!isValidMonth) {
            println("Invalid Month!")
            return
        }
        val transactionList: List<Transaction>? =
            month?.toInt()?.let { financialTrackerStorage.getTransactionsByMonth(it) }
        if (transactionList == null) {
            println("No Transaction found !")
            return
        }
        viewMonthlyReport(transactionList)


    }

    private fun viewMonthlyReport(transactionList: List<Transaction>) {
        transactionList.forEach { transaction ->
            println("id = ${transaction.id},amount = ${transaction.amount}, day = ${transaction.date.day}, month = ${transaction.date.month}, year = ${transaction.date.year}, type = ${transaction.type}, category = ${transaction.category}")
        }
    }


}