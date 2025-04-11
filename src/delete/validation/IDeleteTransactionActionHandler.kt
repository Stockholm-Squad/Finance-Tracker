package src.delete.validation

import src.model.Transaction

interface IDeleteTransactionActionHandler {
    fun checkConfirmation():Boolean
    fun checkID(allTransaction: List<Transaction>, id : String?):Boolean
}