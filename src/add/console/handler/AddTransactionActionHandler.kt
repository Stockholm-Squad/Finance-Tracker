package src.add.console.handler

import src.add.validation.AddTransactionActionValidator
import src.add.validation.IAddTransactionActionValidator
import src.common.console.handler.ActionHandler
import src.model.Transaction
import src.model.TransactionDate
import src.model.TransactionMonth
import src.model.TransactionType
import src.storage.IFinancialTrackerStorage
import java.util.Calendar
import java.util.UUID

class AddTransactionActionHandler : ActionHandler {

    private val validator: IAddTransactionActionValidator = AddTransactionActionValidator()


    // object
    override fun handleAction(financialTrackerStorage: IFinancialTrackerStorage) {
        println("-------------------------- Add Transaction 📝 --------------------------")
        val amount = handleAmount()
        val id = UUID.randomUUID()
        val day = handleDay()
        val month = when (handleMonth()) {
            1 -> TransactionMonth.JANUARY
            2 -> TransactionMonth.FEBRUARY
            3 -> TransactionMonth.MARCH
            4 -> TransactionMonth.APRIL
            5 -> TransactionMonth.MAY
            6 -> TransactionMonth.JUNE
            7 -> TransactionMonth.JULY
            8 -> TransactionMonth.AUGUST
            9 -> TransactionMonth.SEPTEMBER
            10 -> TransactionMonth.OCTOBER
            11 -> TransactionMonth.NOVEMBER
            12 -> TransactionMonth.DECEMBER
            else -> throw IllegalArgumentException("Oops! Invalid month number. 😕 Please enter a valid month.")
        }
        val year = Calendar.getInstance().get(Calendar.YEAR)

        val type = when (handleType()) {
            1 -> TransactionType.INCOME
            2 -> TransactionType.EXPANSES
            else -> throw IllegalArgumentException("Oops! Invalid type. 😕 Please check your input.")
        }
        val category = handleCategory()
        val date = TransactionDate(day = day, month = month, year = year)
        if (financialTrackerStorage.addTransaction(
                Transaction(
                    id = id.toString(),
                    amount = amount,
                    date = date,
                    category = category,
                    type = type
                )
            )
        ) {
            val lastTransaction = financialTrackerStorage.getTransactionById(id.toString())

            println(
                "Category: ${lastTransaction?.category}, " +
                        "Type: ${lastTransaction?.type}, " +
                        "Amount: ${lastTransaction?.amount}, " +
                        "Date: ${lastTransaction?.date}"
            )
        }
    }

    private fun handleAmount(): Double {
        while (true) {
            println(
                "------------------------------------------------------------------------ \n" +
                        "Please enter the amount of the transaction. It should be a valid number. 💸"
            )

            val amount = readln()
            if (validator.validateAmount(amount)) {
                return amount.toDouble()
            } else {
                println("Oops! The amount is not valid. 😕 Please enter a valid number.")
            }
        }
    }

    private fun handleDay(): Int {
        while (true) {
            println(
                "------------------------------------------------------------------------ \n" +
                        "Please enter the day of the transaction. It should be a valid number. 📅"
            )
            val day = readln().trim()
            if (validator.validateDay(day)) {
                return day.toInt()
            } else {
                println("Oops! The day must be between 1 and 31. 😕 Please try again.")
            }
        }
    }

    private fun handleMonth(): Int {
        while (true) {
            println(
                "------------------------------------------------------------------------ \n" +
                        "Please enter the month number of the transaction. 📅" +
                        "1- January\n" +
                        "2- February\n" +
                        "3- March\n" +
                        "4- April\n" +
                        "5- May\n" +
                        "6- June\n" +
                        "7- July\n" +
                        "8- August\n" +
                        "9- September\n" +
                        "10- October\n" +
                        "11- November\n" +
                        "12- December"
            )
            val month = readln().trim()
            if (validator.validateMonth(month)) {
                return month.toInt()
            } else {
                println("Oops! The month must be between 1 and 12. 😕 Please try again.")
            }
        }
    }


    private fun handleType(): Int {
        while (true) {
            println(
                "------------------------------------------------------------------------ \n" +
                        "Please enter the type of the transaction:\n1- Income 💰\n2- Expense 💸" +
                        "------------------------------------------------------------------------ "
            )
            val type = readln().trim()
            if (validator.validateType(type)) {
                return type.toInt()

            } else {
                println("Oops! Invalid input. 😕 Please enter 1 for Income 💰 or 2 for Expense 💸.")
            }
        }
    }

    private fun handleCategory(): String {
        while (true) {
            println(
                "------------------------------------------------------------------------ \n" +
                        "Please enter the category of the transaction ✍: "
            )
            val category = readln().trim()
            if (validator.validateCategory(category)) {
                return category
            } else {
                println("Category not acceptable. Please enter a value between 2 to 25 ❌")
            }
        }
    }

}