package src.add.validation

class AddTransactionActionValidator : IAddTransactionActionValidator {

    override fun validateAmount(amount: String): Boolean {
        when {
            amount.isBlank() -> {
                println("Amount shouldn't be empty or contains spaces ")
                return false
            }

            amount.toDoubleOrNull() == null -> {
                println("Amount shouldn't be character ")
                return false
            }

            amount.toDouble() <= 0.0 -> {
                println("Amount should be greater than 0 ")
                return false
            }
        }
        return true
    }

    override fun validateDay(day: String): Boolean {
        when {
            day.isBlank() -> {
                println("Day shouldn't be empty or contains spaces ")
                return false
            }

            day.toIntOrNull() == null -> {
                println("Day shouldn't be character ")
                return false
            }

            day.toInt() <= 0 -> {
                println("Day should be greater than 0 ")
                return false
            }

            day.toInt() > 31 -> {
                println("Day should be less than 32 ")
                return false
            }

            day.startsWith("0") -> {
                println("Day should not start with 0")
                return false
            }
        }
        return true
    }

    override fun validateMonth(month: String): Boolean {
        when {
            month.isBlank() -> {
                println("Month shouldn't be empty or contains spaces ")
                return false
            }

            month.toIntOrNull() == null -> {
                println("Month shouldn't be character ")
                return false
            }

            month.toInt() <= 0 -> {
                println("Month should be greater than 0 ")
                return false
            }

            month.toInt() > 12 -> {
                println("Month should be less than 32 ")
                return false
            }

            month.startsWith("0") -> {
                println("Month should not start with 0")
                return false
            }
        }
        return true
    }

    override fun validateYear(year: String): Boolean {
        when {
            year.isBlank() -> {
                println("Year shouldn't be empty or contains spaces ")
                return false
            }

            year.toIntOrNull() == null -> {
                println("Year shouldn't be character ")
                return false
            }

            year.toInt() != 2025 -> {
                println("Year should be the current year")
                return false
            }

            year.startsWith("0") -> {
                println("Year should not start with 0")
                return false
            }
        }
        return true
    }

    override fun validateCategory(category: String): Boolean {
        when {
            category.isBlank() -> {
                println("Category shouldn't be empty or contains spaces ")
                return false
            }

            category.length < 2 -> {
                println("Category is too short, it should be more than 2 characters ")
                return false
            }

            category.length > 25 -> {
                println("Category is too long, it should be less than 26 characters ")
            }
        }
        return true
    }

    override fun validateType(type: String): Boolean {
        when {
            type.isBlank() -> {
                println("Type shouldn't be empty or contains spaces ")
                return false
            }

            type.toIntOrNull() == null -> {
                println("Type shouldn't be character ")
                return false
            }

            type.toInt() < 1 || type.toInt() > 2 -> {
                println("Type should be from menu options ")
                return false
            }


        }
        return true
    }
}