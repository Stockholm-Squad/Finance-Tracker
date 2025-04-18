package src.update.validation


interface IUpdateTransactionActionValidator {
    fun isValidateIndex(id: String ): Boolean

    fun isValidOption(option: String): Boolean

    fun isValidateCategory(category: String): Boolean

    fun isValidateType(type: String): Boolean

    fun isValidateAmount(amount: String): Boolean

    fun isValidateDay(day:String):Boolean

    fun isValidateMonth(month:String):Boolean
}