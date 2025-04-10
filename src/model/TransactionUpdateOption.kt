package src.model

enum class TransactionUpdateOption(val option: Int) {
    TYPE(1),
    CATEGORY(2),
    AMOUNT(3),
    DATE(4),
    EXIT(5);
}