package src.common.console.handler

import src.add.console.handler.AddTransactionActionHandler
import src.common.helper.display.DisplayTransactions
import src.common.helper.display.IDisplayTransaction
import src.delete.handler.DeleteTransactionActionHandler
import src.delete.validation.DeleteTransactionActionValidator
import src.report.handler.TransactionReportActionHandler
import src.report.handler.TransactionReportDataHandler
import src.report.validation.TransactionReportActionValidator
import src.storage.IFinancialTrackerStorage
import src.storage.MemoryFinancialTrackerStorage
import src.test.storage.file.FileFinancialTrackerStorage
import src.test.storage.file.FileHelper
import src.view_transaction.handler.ViewTransactionActionHandler
import update.handler.UpdateTransactionActionHandler

class ConsoleHandler {
    private lateinit var financialTrackerStorage: IFinancialTrackerStorage
    private lateinit var helper : IDisplayTransaction

    init {

    }
    fun selectStorageType() {

        println("Select Storage Type: \n1. Memory \n2. File")
        val input = readlnOrNull()
        while(true){
            when (input) {
                "1" -> {handleMemoryStorage();break}
                "2" -> {handleFileStorage();break}
                else -> {
                    println("Invalid Input")
                    continue
                }
            }
        }
    }

    fun handleMemoryStorage() {
        financialTrackerStorage = MemoryFinancialTrackerStorage()
        welcomeMessage()
    }

    fun handleFileStorage() {
        financialTrackerStorage = FileFinancialTrackerStorage(FileHelper("src/financialTracker.txt"))
        welcomeMessage()
    }

    fun welcomeMessage() {
        helper = DisplayTransactions(financialTrackerStorage.getAllTransactions()!!)
        while (true) {
            println(
                "------------------------------------------------------------------------ \n" +
                        "------------------------------------------------------------------------ \n" +
                        "Welcome to Finance Tracker System \n" +
                        "Please choose number for what do you need \n" +
                        "1. Add Transaction\n" +
                        "2. View Transaction \n" +
                        "3. Edit Transaction \n" +
                        "4. Delete Transaction \n" +
                        "5. Provide Monthly Report \n" +
                        "6. Exit \n" +
                        "------------------------------------------------------------------------ "

            )
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
                else -> println("Invalid Input, Please try again 😞")
            }
        }
    }
}

fun main() {
    ConsoleHandler().selectStorageType()
}