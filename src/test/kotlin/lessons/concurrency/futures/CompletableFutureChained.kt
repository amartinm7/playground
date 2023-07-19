package lessons.concurrency.futures

import java.util.concurrent.CompletableFuture
import java.util.function.Supplier

data class Quotation4(val name: String, val value: Int)
class CompletableFutureChained {

    val sleep: (i: Long) -> Unit = { i ->
        Thread.sleep(1000 + i)
    }

    fun execute() {
        val quotationSuppliers: List<Supplier<Quotation4>> = listOf(
            Supplier { Quotation4("apple", 1000).also { sleep(1000) } },
            Supplier { Quotation4("microsoft", 500).also { sleep(2000) } },
            Supplier { Quotation4("google", 2000).also { sleep(3000) } },
        )

        val cf1 = CompletableFuture.supplyAsync(quotationSuppliers[0])
        val cf2 = CompletableFuture.supplyAsync(quotationSuppliers[1])
        val cf3 = CompletableFuture.supplyAsync(quotationSuppliers[2])
        val cf4 = cf1.thenApply { cf2 }.thenApply { cf3 }.join()
        val results = cf4.get()
        println(results)
    }
}

fun main() {
    CompletableFutureChained().execute()
}