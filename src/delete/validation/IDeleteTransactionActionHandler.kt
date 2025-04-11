package src.delete.validation

import src.model.Transaction

interface IDeleteTransactionActionHandler {
    fun checkIDisInt( id: String? ):Boolean
    fun checkConfirmation():Boolean
    fun checkIDinList(allTransaction: List<Transaction>, id : Int):Boolean
}