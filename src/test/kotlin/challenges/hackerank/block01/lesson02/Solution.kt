package challenges.hackerank.block01.lesson02

import java.math.BigInteger

/**
 * Given five positive integers, find the minimum and maximum values that can be calculated by summing exactly four of the five integers. Then print the respective minimum and maximum values as a single line of two space-separated long integers.
 *
 * Example
 *
 * The minimum sum is  and the maximum sum is . The function prints
 *
 * 16 24
 * Function Description
 *
 * Complete the miniMaxSum function in the editor below.
 *
 * miniMaxSum has the following parameter(s):
 *
 * arr: an array of  integers
 * Print
 *
 * Print two space-separated integers on one line: the minimum sum and the maximum sum of  of  elements.
 *
 * Input Format
 *
 * A single line of five space-separated integers.
 *
 * Constraints
 *
 *
 * Output Format
 *
 * Print two space-separated long integers denoting the respective minimum and maximum values that can be calculated by summing exactly four of the five integers. (The output can be greater than a 32 bit integer.)
 *
 * Sample Input
 *
 * 1 2 3 4 5
 * Sample Output
 *
 * 10 14
 * Explanation
 *
 * The numbers are , , , , and . Calculate the following sums using four of the five integers:
 *
 * Sum everything except , the sum is .
 * Sum everything except , the sum is .
 * Sum everything except , the sum is .
 * Sum everything except , the sum is .
 * Sum everything except , the sum is .
 * Hints: Beware of integer overflow! Use 64-bit Integer.
 */
class Solution {
    fun solution(arr: Array<Int>): List<BigInteger> {
        val list = arr.toMutableList().sorted().map { it -> BigInteger.valueOf(it.toLong()) }
        val min = list.slice(0..3).reduce { sum, element -> sum.add(element) }
        val max = list.slice(list.size - 4 until list.size).reduce { sum, element -> sum.add(element) }
        println(listOf(min, max).joinToString(" "))
        return listOf(min, max)
    }

    fun miniMaxSum(arr: Array<Int>): Unit {
        solution(arr).also { println(it.joinToString(" ")) }
    }

    fun main(args: Array<String>) {

        val arr = readLine()!!.trimEnd().split(" ").map { it.toInt() }.toTypedArray()

        miniMaxSum(arr)
    }

}