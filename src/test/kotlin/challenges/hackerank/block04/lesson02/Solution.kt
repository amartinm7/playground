package challenges.hackerank.block04.lesson02

import java.math.BigInteger

/**
 * Complete the 'superdigit' function below.
 *
 * repeat the string "n", k times. So if the n = 1234, and k = 4 the final string is 1234123412341234.
 * convert the string to IntArray and do the sum().
 * if the sum >= 10 then do the sum of the digit
 * if the sum < 10 then return the value, which is the super digit
 */
class Solution {

    /**
     * this solution performs better for big data
     */
    private tailrec fun doSolution(n: String, k: Int): Int {
        var counter = BigInteger.ZERO
        for (i in n.indices) {
            counter = counter.add(n.slice(i..i).toBigInteger())
        }
        val sum = counter.multiply(k.toBigInteger())
        return when {
            sum > 9.toBigInteger() -> doSolution(sum.toString(), 1)
            else -> sum.toInt()
        }
    }

    /**
     * this solution don't performs to big data
     */
    private tailrec fun doSolution2(n: String, k: Int): Int {
        // val sum = n.toCharArray().map { it.digitToInt() }.sum()
        val sum = n.toCharArray().map { Character.getNumericValue(it) }.sum() * k
        return when {
            sum > 9 -> doSolution(sum.toString(), 1)
            else -> sum
        }
    }

    fun solution(n: String, k: Int): Int {
        return doSolution(n, k)
    }
}

/*
 * Complete the 'superDigit' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. STRING n
 *  2. INTEGER k
 */

fun superDigit(n: String, k: Int): Int {
    // Write your code here
    return Solution().solution(n, k)
}

fun main(args: Array<String>) {
    val first_multiple_input = readLine()!!.trimEnd().split(" ")

    val n = first_multiple_input[0]

    val k = first_multiple_input[1].toInt()

    val result = superDigit(n, k)

    println(result)
}
