package src.model

enum class TransactionUpdateOption(val option: Int) {
    CATEGORY(1),
    TYPE(2),
    AMOUNT(3),
    DATE(4),
    EXIT(5);
}