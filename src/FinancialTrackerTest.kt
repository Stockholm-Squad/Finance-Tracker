package src.test

fun main() {
    val addTransactionTest = AddTransactionTest()
    val updateTransactionTest = UpdateTransactionTest()
    val viewTransactionTest = ViewTransactionTest()
    val deleteTransactionTest = DeleteTransactionTest()
    val monthlyReportTest = MonthlyReportTest()
    addTransactionTest.runTest()
    updateTransactionTest.runTest()
    viewTransactionTest.runTest()
    deleteTransactionTest.runTest()
    monthlyReportTest.runTest()


}