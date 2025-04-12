package src.report.handler

import src.common.console.handler.ActionHandler
import src.model.Transaction
import src.model.TransactionMonth
import src.report.validation.ITransactionReportActionValidator
import src.storage.IFinancialTrackerStorage

class TransactionReportActionHandler(
    private val transactionReportActionValidator: ITransactionReportActionValidator,
    private val transactionReportDataHandler: ITransactionReportDataHandler
) : ActionHandler {

    override fun handleAction(financialTrackerStorage: IFinancialTrackerStorage) {
        println(
            "------------------------------------------------------------------------ \n" +
                    "Please Enter Month to view Monthly Report : "
        )
        println(
            "like ${TransactionMonth.JANUARY.orderId}.${TransactionMonth.JANUARY.name}" +
                    ", ${TransactionMonth.FEBRUARY.orderId}.${TransactionMonth.FEBRUARY.name}" +
                    ", ${TransactionMonth.MARCH.orderId}.${TransactionMonth.MARCH.name}" +
                    ", ${TransactionMonth.APRIL.orderId}.${TransactionMonth.APRIL.name}"
        )
        val month = readlnOrNull()


        val isValidMonth = transactionReportActionValidator.isMonthValid(month)
        if (!isValidMonth) {
            println("Oops! Invalid Month! 😕 Please enter a month between 1 and 12.")
            return
        }

        val transactionList: List<Transaction>? =
            transactionReportDataHandler.getMonthlyReport(month, financialTrackerStorage)

        if (transactionList.isNullOrEmpty()) {
            println("Oops! No transaction found. 😕 Please check and try again.")
            return
        }

        viewMonthlyReport(transactionList)


    }

    private fun viewMonthlyReport(transactionList: List<Transaction>) {
        for (i in transactionList.indices) {
            println(
                "Transaction Number:${i + 1}\n" +
                        "amount = ${transactionList[i].amount}, " +
                        "day = ${transactionList[i].date.day}, " +
                        "month = ${transactionList[i].date.month}, " +
                        "year = ${transactionList[i].date.year}, " +
                        "type = ${transactionList[i].type}, " +
                        "category = ${transactionList[i].category}"
            )

        }
    }


}