package src.model

data class Transaction(
    val id: String,
    val amount: Double,
    val date: TransactionDate,
    val category: String,
    val type: TransactionType,
)
