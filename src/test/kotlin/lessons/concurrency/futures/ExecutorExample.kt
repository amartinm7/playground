package lessons.concurrency.futures

import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.function.Supplier

data class Quotation2(val name: String, val value: Int)
class ExecutorExample {

    val quotationExec: Supplier<Quotation2> =
        Supplier {
            Thread.sleep(1000)
            Quotation2("name", 1000).also { println(it) }
        }

    val quotationExec1: Callable<Quotation2> =
            Callable {
                Thread.sleep(1000)
                Quotation2("name", 1000).also { println(it) }
            }

    val quotationExec2: Callable<Quotation2> =
        Callable {
            Thread.sleep(1000)
            Quotation2("name", 1000).also { println(it) }
        }

    val quotationExec3: Callable<Quotation2> =
        Callable {
            Thread.sleep(1000)
            Quotation2("name", 1000).also { println(it) }
        }

    fun execute() {
        val executor: ExecutorService = Executors.newFixedThreadPool(4);

        val list = listOf(
            quotationExec1,
            quotationExec2,
            quotationExec3,
        )

        val futures = executor.invokeAll(list)
        // futures.map { it.get() } no needed in this example

        executor.shutdown()
    }
}

class SyncExampleWithSuppliers {

    val quotationSupplier =
        { time: Long, name: String, value: Int ->
            Supplier {
                Thread.sleep(time)
                Quotation2(name, value).also { println(it) }
            }
        }
    fun <T> toCallable(supplier: Supplier<T>): Callable<T> =
        Callable { supplier.get() }
    fun execute() {
        val executor: ExecutorService = Executors.newFixedThreadPool(4)

        val list = listOf(
            quotationSupplier(300, "apple", 1300),
            quotationSupplier(100, "microsoft", 1100),
            quotationSupplier(200, "google", 200)
        )

        val futures = list.map { executor.submit(toCallable(it)) }

        futures.map { it.get() }

        executor.shutdown()
    }
}

fun main() {
    SyncExample().execute()
    SyncExampleWithSuppliers().execute()
}