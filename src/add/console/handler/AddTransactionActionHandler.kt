package add.console.handler

import common.console.handler.ActionHandler
import storage.IFinancialTrackerRepository

class AddTransactionActionHandler : ActionHandler {
    override fun handleAction(financialTrackerRepository: IFinancialTrackerRepository) {
        TODO("Not yet implemented")
        //print and get inputs one by one and call validation that will be implemented in AddTransactionActionValidator
        // if all data valid call add in financialTrackerRepository
    }

}