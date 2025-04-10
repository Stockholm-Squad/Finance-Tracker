package src.add.console.handler

import src.common.console.handler.ActionHandler
import src.storage.IFinancialTrackerStorage

class AddTransactionActionHandler : ActionHandler {
    override fun handleAction(financialTrackerStorage: IFinancialTrackerStorage) {
        TODO("Not yet implemented")
        //print and get inputs one by one and call validation that will be implemented in AddTransactionActionValidator
        // if all data valid call add in financialTrackerRepository
    }

}