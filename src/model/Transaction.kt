package src.model

data class Transaction(
    val id: Int,
    val amount: Double,
    val date: TransactionDate,
    val category: TransactionCategory,
    val type: String
)
