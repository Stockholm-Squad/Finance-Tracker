package src.report.validation

import src.model.TransactionMonth


class TransactionReportActionValidator : ITransactionReportActionValidator {

    override fun isMonthValid(month: String?): Boolean {
        val trimMonth = month?.trim() ?: return false
        if (trimMonth.isEmpty()) return false
        if (trimMonth.first() == '0') return false
        return isTransactionMonth(trimMonth)
    }

    private fun isTransactionMonth(trimMonth: String): Boolean {
        val isDigit = trimMonth.all { it.isDigit() }
        if (isDigit) {
            val monthAsInt = trimMonth.toInt()
            if (TransactionMonth.getTransactionMonth(monthAsInt) != null) return true
        }
        return TransactionMonth.getTransactionMonth(trimMonth) != null
    }
}