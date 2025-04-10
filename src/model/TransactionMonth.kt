package src.model

enum class TransactionMonth(val orderId: Int) {
    JANUARY(1),
    FEBRUARY(2),
    MARCH(3),
    APRIL(4),
    MAY(5),
    JUNE(6),
    JULY(7),
    AUGUST(8),
    SEPTEMBER(9),
    OCTOBER(10),
    NOVEMBER(11),
    DECEMBER(12);

    companion object {

        fun getTransactionMonth(transactionMonthName: String?): TransactionMonth? {
            if (transactionMonthName == null) return null
            entries.forEach { transactionMonth ->
                if (transactionMonth.name == transactionMonthName) return transactionMonth
            }
            return null
        }

        fun getTransactionMonth(transactionMonthOrderId: Int?): TransactionMonth? {
            if (transactionMonthOrderId == null) return null
            entries.forEach { transactionMonth ->
                if (transactionMonth.orderId == transactionMonthOrderId) return transactionMonth
            }
            return null
        }
    }
}