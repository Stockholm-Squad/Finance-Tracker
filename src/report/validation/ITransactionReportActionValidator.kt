package src.report.validation

interface ITransactionReportActionValidator {
    fun isMonthValid(month: String?): Boolean
}