package lessons.language

class Experiment {

}

infix fun Int.suma(x: Int): Int = this + x
infix fun Int.divide(x: Int): Int = this / x
fun <T> asList(vararg args: T): List<T> {
    val arr = ArrayList<T>()
    for (item in args) {
        arr.add(item)
    }
    return arr.toList()
}

typealias onBlurI = () -> Unit

typealias transformable = (string: String, times: Int) -> String

// A higher-order function is a function that takes functions as parameters, or returns a function.
val onClick: () -> Unit = { println(1 suma 2) }

val onBlur: onBlurI = { println(15 divide 5) }

val sumai = { i: Int -> i + i }

val sumados = { i: Int, j: Int -> i + j }

val repeat = { string: String, times: Int -> string.repeat(times) }

fun runTransformation(f: (string: String, times: Int) -> String): String = f("hello", 4)
fun runTransformation2(f: transformable): String = f("hello", 4)

val intPlus: Int.(Int) -> Int = Int::plus

fun main(args: Array<String>) {
    println(1 suma 2)
    println(15 divide 5)
    println(asList(1, 2, 3))
    println(asList(*intArrayOf(1, 2, 3, 4, 5).toTypedArray()))
    println(onClick)
    onClick()
    onBlur()
    println(sumai(7))
    println(sumados(7, 9))
    println(runTransformation(repeat))
    println(runTransformation2(repeat))
    println(2.intPlus(3))
    println(listOf<Int>(1, 2, 4, 5, 6).filter { i -> i % 2 == 0 }.fold(0) { acc, item -> acc + item })
}
