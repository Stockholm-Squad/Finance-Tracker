package src.model

import java.io.Serializable

data class TransactionDate(
    val day: Int,
    val month: TransactionMonth,
    val year: Int
): Serializable
