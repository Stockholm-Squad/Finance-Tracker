package common.console.handler

import add.console.handler.AddTransactionActionHandler
import storage.FinancialTrackerRepository
import storage.IFinancialTrackerRepository
import delete.handler.DeleteTransactionActionHandler
import report.handler.TransactionReportActionHandler
import storage.MemoryFinancialTrackerStorage
import update.handler.UpdateTransactionActionHandler
import view_transaction.handler.ViewTransactionActionHandler

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
            val financialTrackerRepository: IFinancialTrackerRepository =
                FinancialTrackerRepository(MemoryFinancialTrackerStorage())
            when (choice) {
                "1" -> AddTransactionActionHandler().handleAction(financialTrackerRepository)
                "2" -> ViewTransactionActionHandler().handleAction(financialTrackerRepository)
                "3" -> UpdateTransactionActionHandler().handleAction(financialTrackerRepository)
                "4" -> DeleteTransactionActionHandler().handleAction(financialTrackerRepository)
                "5" -> TransactionReportActionHandler().handleAction(financialTrackerRepository)
                "6" -> break
                else -> println("Invalid Input, Please try again")
            }
        }
    }
}

fun main() {
    ConsoleHandler().welcomeMessage()
}