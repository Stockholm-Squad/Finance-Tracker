package model

data class Transaction(
    val id: Int,
    val amount: Double,
    val date: TransactionDate,
    val category: String,
    val type: TransactionType,
)
