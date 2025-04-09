package common.console.handler

import storage.IFinancialTrackerRepository

interface ActionHandler {
    fun handleAction(
        financialTrackerRepository: IFinancialTrackerRepository
    )
}