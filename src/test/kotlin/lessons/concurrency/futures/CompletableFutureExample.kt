package lessons.concurrency.futures

import java.util.concurrent.CompletableFuture
import java.util.function.Supplier

data class Quotation3(val name: String, val value: Int)
class CompletableFutureExample {

    val sleep : (i: Long) -> Unit = { i ->
        Thread.sleep(1000 + i)
    }
    fun execute() {
        val quotationSuppliers: List<Supplier<Quotation3>> = listOf(
            Supplier { Quotation3("apple", 1000).also { sleep(1000) } },
            Supplier { Quotation3("microsoft", 500).also { sleep(2000) } },
            Supplier { Quotation3("google", 2000).also { sleep(3000) } },
        )

        val futures = quotationSuppliers.map { CompletableFuture.supplyAsync(it) }
        CompletableFuture.allOf(*futures.toTypedArray()).join()
        // hasta que no terminan todas las tareas no puede pasar a hacer el get
        // aqui si que hace falta estas lineas para obtener los valores
        val results = futures.map { it.get() }
        results.forEach { println(it) }
    }
}

fun main() {
    CompletableFutureExample().execute()
}