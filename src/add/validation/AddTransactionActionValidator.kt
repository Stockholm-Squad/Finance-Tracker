package src.add.validation


class AddTransactionActionValidator : IAddTransactionActionValidator {

    override fun validateAmount(amount: String): Boolean {
        when {
            amount.isBlank() -> return false

            amount.toDoubleOrNull() == null -> return false

            amount.toDouble() <= 0.0 -> return false
        }
        return true
    }

    override fun validateDay(day: String): Boolean {
        when {
            day.isBlank() -> return false

            day.toIntOrNull() == null -> return false

            day.toInt() <= 0 -> return false

            day.toInt() > 31 -> return false

            day.startsWith("0") -> return false
        }
        return true
    }

    override fun validateMonth(month: String): Boolean {
        when {
            month.isBlank() -> return false

            month.toIntOrNull() == null -> return false

            month.toInt() <= 0 -> return false

            month.toInt() > 12 -> return false

            month.startsWith("0") -> return false
        }
        return true
    }


    override fun validateCategory(category: String): Boolean {
        when {
            category.isBlank() -> return false

            category.length < 2 -> return false

            category.length > 25 -> return false
        }
        return true
    }

    override fun validateType(type: String): Boolean {
        when {
            type.isBlank() -> return false

            type.toIntOrNull() == null -> return false

            type.toInt() < 1 || type.toInt() > 2 -> return false
        }
        return true
    }
}