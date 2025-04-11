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
        println("------------------------------------Add Transaction---------------------------------")
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
            else -> throw IllegalArgumentException("Invalid month number")
        }
        val year = Calendar.getInstance().get(Calendar.YEAR)

        val type = when (handleType()) {
            1 -> TransactionType.INCOME
            2 -> TransactionType.EXPANSES
            else -> throw IllegalArgumentException("Invalid type")
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
                "Transaction Amount is  ${lastTransaction?.amount}\n" +
                        "Transaction Date is ${lastTransaction?.date?.day}-${lastTransaction?.date?.month}-${lastTransaction?.date?.year}\n" +
                        "Transaction Type is ${lastTransaction?.type}\n" +
                        "Transaction Category ${lastTransaction?.category}"
            )
        }
    }

    private fun handleAmount(): Double {
        while (true) {
            println("Please Enter the amount of the transaction")
            println("Transaction should be a number")
            val amount = readln()
            if (validator.validateAmount(amount)) {
                return amount.toDouble()
            } else {
                println("Invalid input")
            }
        }
    }

    private fun handleDay(): Int {
        while (true) {
            println("Please Enter the day of the transaction")
            println("Day should be a number")
            val day = readln()
            if (validator.validateDay(day)) {
                return day.toInt()
            } else {
                println("Invalid input")
            }
        }
    }

    private fun handleMonth(): Int {
        while (true) {
            println("Please enter the month number of the transaction")
            println(
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
            val month = readln()
            if (validator.validateMonth(month)) {
                return month.toInt()
            } else {
                println("Invalid input")
            }
        }
    }


    private fun handleType(): Int {
        while (true) {
            println("Please Enter the type of the transaction")
            println(
                "1- Income\n" +
                        "2- Expanse"
            )
            val type = readln()
            if (validator.validateType(type)) {
                return type.toInt()

            } else {
                println("Invalid input")
            }
        }
    }

    private fun handleCategory(): String {
        while (true) {
            println("Please Enter the Category of the transaction")
            val category = readln()
            if (validator.validateCategory(category)) {
                return category
            } else {
                println("Invalid input")
            }
        }
    }

}