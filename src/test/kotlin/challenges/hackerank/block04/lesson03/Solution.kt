package challenges.hackerank.block04.lesson03

/**
 * Complete the 'bribes in a row' function below.
 *
 * given a row 2 1 5 3 4 to achieve 1 2 3 4 5, the #2 in moved 1 position and the #5 is moved 2 positions
 * so at the end the result is 3
 *
 * every number can move 2 times at much
 *
 * if it's not possible to 12345 it's returned Too chaotic
 */
class Solution {

    tailrec fun swap(q: Array<Int>, map: MutableMap<Int, Int>, counter: Int): Int {
        var currentCounter = counter
        for (i in 0 until q.size - 1) {
            val slice = q.slice(i..i + 1)
            val orderedSlice = slice.sorted()
            if (slice != orderedSlice) {
                if (map[slice[0]] == 2) {
                    currentCounter = -1
                    break
                } else {
                    map[slice[0]] = map[slice[0]]?.plus(1) ?: 1
                }
                val newArray = (q.slice(0 until i) + orderedSlice + q.slice(i + 2 until q.size)).toTypedArray()
                currentCounter = swap(newArray, map, ++currentCounter)
                break
            }
        }
        return currentCounter;
    }

    fun solution(q: Array<Int>): Int {
        val counter = swap(q, mutableMapOf(), 0)
        if ((q.size - 1) * 2 > counter) return counter else return -1
    }
}

/*
 * Complete the 'minimumBribes' function below.
 *
 * The function accepts INTEGER_ARRAY q as parameter.
 */

fun minimumBribes(q: Array<Int>): Unit {
    // Write your code here
    when (val counter = Solution().solution(q)) {
        -1 -> println("Too chaotic")
        else -> println(counter)
    }
}

fun main(args: Array<String>) {
    val t = readLine()!!.trim().toInt()

    for (tItr in 1..t) {
        val n = readLine()!!.trim().toInt()

        val q = readLine()!!.trimEnd().split(" ").map { it.toInt() }.toTypedArray()

        minimumBribes(q)
    }
}