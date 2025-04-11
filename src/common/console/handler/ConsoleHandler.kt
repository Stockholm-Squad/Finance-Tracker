package src.common.console.handler

import src.add.console.handler.AddTransactionActionHandler
import src.delete.handler.DeleteTransactionActionHandler
import src.delete.validation.DeleteTransactionActionValidator
import src.model.Transaction
import src.model.TransactionDate
import src.model.TransactionMonth
import src.model.TransactionType
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
            dummyData(financialTrackerStorage)
            when (choice) {
                "1" -> AddTransactionActionHandler().handleAction(financialTrackerStorage)
                "2" -> ViewTransactionActionHandler().handleAction(financialTrackerStorage)
                "3" -> UpdateTransactionActionHandler().handleAction(financialTrackerStorage)
                "4" -> DeleteTransactionActionHandler(DeleteTransactionActionValidator()).handleAction(financialTrackerStorage)
                "5" -> TransactionReportActionHandler().handleAction(financialTrackerStorage)
                "6" -> break
                else -> println("Invalid Input, Please try again")
            }
        }
    }

    // for delete
    fun dummyData(financialTrackerStorage: IFinancialTrackerStorage){

        (financialTrackerStorage as MemoryFinancialTrackerStorage).allTransaction.add(
            Transaction(
                id = 1,
                amount = 1000.0,
                date = TransactionDate(1, TransactionMonth.APRIL,2023),
                category = "Food",
                type = TransactionType.EXPANSES
            )
        )
        (financialTrackerStorage as MemoryFinancialTrackerStorage).allTransaction.add(
            Transaction(
                id = 2,
                amount = 1000.0,
                date = TransactionDate(1, TransactionMonth.APRIL,2023),
                category = "Food",
                type = TransactionType.EXPANSES
            )
        )
        (financialTrackerStorage as MemoryFinancialTrackerStorage).allTransaction.add(
            Transaction(
                id = 3,
                amount = 1000.0,
                date = TransactionDate(1, TransactionMonth.APRIL,2023),
                category = "Food",
                type = TransactionType.EXPANSES
            )
        )
        (financialTrackerStorage as MemoryFinancialTrackerStorage).allTransaction.add(
            Transaction(
                id = 4,
                amount = 1000.0,
                date = TransactionDate(1, TransactionMonth.APRIL,2023),
                category = "Food",
                type = TransactionType.EXPANSES
            )
        )
    }
}

fun main() {
    ConsoleHandler().welcomeMessage()
}