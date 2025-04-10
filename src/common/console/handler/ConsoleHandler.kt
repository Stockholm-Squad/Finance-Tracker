package src.common.console.handler

import src.add.console.handler.AddTransactionActionHandler
import src.delete.handler.DeleteTransactionActionHandler
import src.report.handler.TransactionReportActionHandler
import src.storage.IFinancialTrackerStorage
import src.storage.MemoryFinancialTrackerStorage
import src.update.handler.UpdateTransactionActionHandler
import src.view_transaction.handler.ViewTransactionActionHandler

class ConsoleHandler {
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
            val financialTrackerStorage: IFinancialTrackerStorage = MemoryFinancialTrackerStorage()
            when (choice) {
                "1" -> AddTransactionActionHandler().handleAction(financialTrackerStorage)
                "2" -> ViewTransactionActionHandler().handleAction(financialTrackerStorage)
                "3" -> UpdateTransactionActionHandler().handleAction(financialTrackerStorage)
                "4" -> DeleteTransactionActionHandler().handleAction(financialTrackerStorage)
                "5" -> TransactionReportActionHandler().handleAction(financialTrackerStorage)
                "6" -> break
                else -> println("Invalid Input, Please try again")
            }
        }
    }
}

fun main() {
    ConsoleHandler().welcomeMessage()
}