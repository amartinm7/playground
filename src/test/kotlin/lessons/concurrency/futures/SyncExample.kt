package lessons.concurrency.futures

data class Quotation1(val name: String, val value: Int)
class SyncExample {

    val quotationExec = { time: Long, name: String, value: Int ->
        Thread.sleep(time)
        Quotation1(name, value).also { println(it) }
    }

    fun execute() {
        val list = listOf(
            quotationExec(1000, "apple", 1000),
            quotationExec(2000, "microsoft", 500),
            quotationExec(3000, "google", 2000),
        )

        list.stream()
            .map { quotationExec }
            .findFirst()
            .orElseThrow()
    }
}

fun main() {
    SyncExample().execute()
}