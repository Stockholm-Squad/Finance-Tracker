package src.report.validation


class TransactionReportActionValidator : ITransactionReportActionValidator {
    override fun isMonthValid(month: String?): Boolean {

        val trimMonth = month?.trim() ?: return false
        if (trimMonth.isEmpty()) return false
        if (trimMonth.first() == '0') return false
        val isDigit = trimMonth.all { it.isDigit() }
        if (!isDigit) return false
        val monthAsDigit = trimMonth.toInt()
        return !(monthAsDigit < 1 || monthAsDigit > 12)

    }
}