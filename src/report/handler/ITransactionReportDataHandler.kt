package src.report.handler

import src.model.Transaction
import src.storage.IFinancialTrackerStorage

interface ITransactionReportDataHandler {
    fun getMonthlyReport(
        month: String?,
        financialTrackerStorage: IFinancialTrackerStorage
    ): List<Transaction>?
}