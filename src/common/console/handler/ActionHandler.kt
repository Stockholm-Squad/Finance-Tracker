package src.common.console.handler

import src.storage.IFinancialTrackerStorage

interface ActionHandler {
    fun handleAction(
        financialTrackerRepository: IFinancialTrackerStorage
    )
}