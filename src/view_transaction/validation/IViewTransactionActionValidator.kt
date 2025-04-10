package src.view_transaction.validation

interface IViewTransactionActionValidator {

    fun validateIdNotString(id:String):Boolean
    fun validateIdNotOutOfRange(id:Int,transactionsSize:Int):Boolean
    fun validateId(id:String?,transactionsSize: Int):Boolean
}