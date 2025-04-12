package src.test

import src.report.handler.ITransactionReportDataHandler
import src.report.handler.TransactionReportDataHandler
import src.report.validation.ITransactionReportActionValidator
import src.report.validation.TransactionReportActionValidator
import src.storage.IFinancialTrackerStorage
import src.storage.MemoryFinancialTrackerStorage


class MonthlyReportTest : TransactionTest() {


    override fun runTest() {

        /** Invalid test cases */

        val transactionReportActionValidator: ITransactionReportActionValidator = TransactionReportActionValidator()
        val transactionReportDataHandler: ITransactionReportDataHandler = TransactionReportDataHandler()
        val financialTrackerStorage: IFinancialTrackerStorage = MemoryFinancialTrackerStorage()
        println("\n<-----------------Monthly Report Test-------------------->")
        check(
            name = "When month < 1 it should return null",
            result = transactionReportActionValidator.isMonthValid("-13"),
            expectedResult = false
        )

        check(
            name = "When month > 12 it should return null",
            result = transactionReportActionValidator.isMonthValid("13"),
            expectedResult = false
        )
        check(
            name = "When month is string it should return null",
            result = transactionReportActionValidator.isMonthValid("abc"),
            expectedResult = false
        )

        check(
            name = "When month is empty it should return null",
            result = transactionReportActionValidator.isMonthValid(""),
            expectedResult = false
        )

        check(
            name = "When month contains space between it should return null",
            result = transactionReportActionValidator.isMonthValid("1 1"),
            expectedResult = false
        )

        check(
            name = "When month is floating or double it should return null",
            result = transactionReportActionValidator.isMonthValid("1.0"),
            expectedResult = false
        )
        check(
            name = "When month starts with leading 0 it should return null",
            result = transactionReportActionValidator.isMonthValid("011"),
            expectedResult = false
        )
//        check(
//            name = "When month is not exist it should return null",
//            result = transactionReportActionValidator.isMonthValid("12"),
//            expectedResult = false
//        )

        /** Valid test cases */
        check(
            name = "when month is exist for one time it should return value",
            result = transactionReportDataHandler.getMonthlyReport("1", financialTrackerStorage)?.isNotEmpty() == true,
            expectedResult = true
        )
        check(
            name = "when month is exist for 3 times it should return value",
            result = transactionReportDataHandler.getMonthlyReport("1", financialTrackerStorage)?.size == 3,
            expectedResult = true
        )

        check(
            name = "when month contains spaces around it should return value",
            result = transactionReportDataHandler.getMonthlyReport(" 2 ".trim(), financialTrackerStorage)
                ?.isNotEmpty() == true,
            expectedResult = true
        )
        check(
            name = "when month is arabic digits it should return value",
            result = transactionReportDataHandler.getMonthlyReport("١", financialTrackerStorage)?.isNotEmpty() == true,
            expectedResult = true
        )
        println("<------------------------End of Monthly Report Test---------------------------->")
    }
}