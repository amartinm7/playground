package lessons.language.lambda

class Solution {
}

val sum = {a: Int, b: Int -> a + b}

val div = { a: Int, b:Int -> a * b }
fun multiply(a: Int, b:Int): Int = a * b
fun applyFunc (a: Int, b: Int, block: (c:Int, d:Int) -> Int): Int = block(a,b)

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    val arr = readLine()!!.trimEnd().split(" ").map { it.toInt() }.toTypedArray()

    applyFunc(1,2, ::multiply)
    applyFunc(1,2, div)
    applyFunc(1,2, sum)

}