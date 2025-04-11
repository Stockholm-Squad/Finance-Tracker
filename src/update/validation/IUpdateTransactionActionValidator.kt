package src.update.validation


interface IUpdateTransactionActionValidator {
    fun isValidateId(id: String, ): Boolean

    fun isValidOption(option: String): Boolean

    fun isValidateCategory(category: String): Boolean

    fun isValidateType(type: String): Boolean

    fun isValidateAmount(amount: String): Boolean

    fun isValidateDate(date: String): Boolean
}