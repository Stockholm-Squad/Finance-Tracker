package src.add.validation



interface IAddTransactionActionValidator {
    fun validateAmount(amount: String): Boolean
    fun validateDay(day: String): Boolean
    fun validateMonth(month: String): Boolean
    fun validateYear(year: String): Boolean
    fun validateCategory(category: String): Boolean
    fun validateType(type:String): Boolean
}