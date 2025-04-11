package src.common.console.handler

import src.add.console.handler.AddTransactionActionHandler
import src.common.helper.display.DisplayTransactions
import src.delete.handler.DeleteTransactionActionHandler
import src.delete.validation.DeleteTransactionActionValidator
import src.report.handler.TransactionReportActionHandler
import src.report.handler.TransactionReportDataHandler
import src.report.validation.TransactionReportActionValidator
import src.storage.IFinancialTrackerStorage
import src.storage.MemoryFinancialTrackerStorage
import src.view_transaction.handler.ViewTransactionActionHandler
import update.handler.UpdateTransactionActionHandler

class ConsoleHandler {
    private val financialTrackerStorage: IFinancialTrackerStorage = MemoryFinancialTrackerStorage()
    private val helper = DisplayTransactions(financialTrackerStorage.getAllTransactions()!!)
    fun welcomeMessage() {
        while (true) {
            println("----------------------------------------------")
            println("----------------------------------------------")
            println(
                "Welcome to Finance Tracker System \n" +
                        "Please choose number for what do you need \n" +
                        "1. Add Transaction\n" +
                        "2. View Transaction \n" +
                        "3. Edit Transaction \n" +
                        "4. Delete Transaction \n" +
                        "5. Provide Monthly Report \n" +
                        "6. Exit "
            )
            println("----------------------------------------------")
            val choice = readlnOrNull()
            when (choice) {
                "1" -> AddTransactionActionHandler().handleAction(financialTrackerStorage)
                "2" -> ViewTransactionActionHandler().handleAction(financialTrackerStorage)
                "3" -> UpdateTransactionActionHandler().handleAction(financialTrackerStorage)
                "4" -> DeleteTransactionActionHandler(
                    DeleteTransactionActionValidator(), helper).handleAction(financialTrackerStorage)
                "5" -> TransactionReportActionHandler(
                    TransactionReportActionValidator(), TransactionReportDataHandler()
                ).handleAction(financialTrackerStorage)
                "6" -> break
                else -> println("Invalid Input, Please try again")
            }
        }
    }
}

fun main() {
    ConsoleHandler().welcomeMessage()
}