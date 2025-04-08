package src.test

enum class TransactionMonthlyReportTestCase(
    val month: String,
    val testCaseMessage: String,
) {
    TEST_CASE1("-1", "When have negative number it should return false"),
}