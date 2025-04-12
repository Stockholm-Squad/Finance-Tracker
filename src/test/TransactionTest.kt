package src.test

abstract class TransactionTest {
    abstract fun runTest()
    open fun check(name: String, result: Boolean, expectedResult: Boolean) {
        if (result == expectedResult) {
            println("✅ Success $name")
        } else {
            println("❌ Failed $name")
        }
    }

}