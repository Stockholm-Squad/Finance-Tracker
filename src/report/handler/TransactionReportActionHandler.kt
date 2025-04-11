package src.report.handler

import src.common.console.handler.ActionHandler
import src.model.Transaction
import src.model.TransactionMonth
import src.report.validation.ITransactionReportActionValidator
import src.report.validation.TransactionReportActionValidator
import src.storage.IFinancialTrackerStorage

class TransactionReportActionHandler : ActionHandler {

    private val transactionReportActionValidator: ITransactionReportActionValidator = TransactionReportActionValidator()
    private val transactionReportDataHandler: ITransactionReportDataHandler = TransactionReportDataHandler()

    override fun handleAction(financialTrackerStorage: IFinancialTrackerStorage) {
        println("Please Enter Month to view Monthly Report : ")
        println("like ${TransactionMonth.JANUARY.orderId}.${TransactionMonth.JANUARY.name}" +
                ", ${TransactionMonth.FEBRUARY.orderId}.${TransactionMonth.FEBRUARY.name}"+
                ", ${TransactionMonth.MARCH.orderId}.${TransactionMonth.MARCH.name}"+
                ", ${TransactionMonth.APRIL.name}.${TransactionMonth.APRIL.name}"
        )
        val month = readlnOrNull()

        val isValidMonth = transactionReportActionValidator.isMonthValid(month)
        if (!isValidMonth) {
            println("Invalid Month!")
            return
        }

        val transactionList: List<Transaction>? =
            transactionReportDataHandler.getMonthlyReport(month, financialTrackerStorage)

        if (transactionList == null) {
            println("No Transaction found!")
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