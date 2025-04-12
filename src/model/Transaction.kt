package src.model

import java.io.Serializable

data class Transaction(
    val id: String,
    val amount: Double,
    val date: TransactionDate,
    val category: String,
    val type: TransactionType,
) : Serializable
