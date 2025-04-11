package src.report.handler

import src.model.Transaction
import src.model.TransactionMonth
import src.storage.IFinancialTrackerStorage

class TransactionReportDataHandler : ITransactionReportDataHandler {
    override fun getMonthlyReport(
        month: String?,
        financialTrackerStorage: IFinancialTrackerStorage
    ): List<Transaction>? {
        val transactionMonth = this.getTransactionMonth(month)
        val transactionList: List<Transaction>? =
            transactionMonth?.let { financialTrackerStorage.getTransactionsByMonth(it) }
        return transactionList
    }

    private fun getTransactionMonth(month: String?): TransactionMonth? {
        if (month == null) return null
        var transactionMonth = TransactionMonth.getTransactionMonth(month)
        if (transactionMonth != null) {
            return transactionMonth
        }
        try {
            transactionMonth = TransactionMonth.getTransactionMonth(month.toInt())
            if (transactionMonth != null) return transactionMonth
        } catch (t: Throwable) {
            return null
        }
        return null
    }
}