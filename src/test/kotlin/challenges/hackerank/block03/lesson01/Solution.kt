package challenges.hackerank.block03.lesson01

/**
 * findZigZagSequence
 *
 * 1 2 3 4 5 6 7
 *
 * 1 2 3 7 6 5 4
 *
 *
 * 88 10 35 21 12 56 20 44 81 27 38 24 94 55 70 16 68 48 14 92 26 4 3 71 37 17 49 52 11 73 50 1 36 98 58 65 90 54 6 45 95 74 30 99 34 93 66 25 18 32 64 62 57 40 67 80 22 33 60 47 72 87 78 46 100 8 2 53 97 69 15 7 19 85 77 31 84 9 59 89 83 23 13 63 76 28 51 86 82 5 41 61 39 29 96 43 75 79 91
 *
 * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 43 44 45 46 47 48 49 50 100 99 98 97 96 95 94 93 92 91 90 89 88 87 86 85 84 83 82 81 80 79 78 77 76 75 74 73 72 71 70 69 68 67 66 65 64 63 62 61 60 59 58 57 56 55 54 53 52 51
 *
 * Now if we permute the array as , the result is a zig zag sequence.
 *
 * Debug the given function findZigZagSequence to return the appropriate zig zag sequence for the given input array.
 */
class Solution {

    fun solution(arr: String): String {
        val a = arr.split(" ").map{ it.toInt() }.toTypedArray().sorted()
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
        return (a.slice(0..a.size/2 -1 ) + a[a.size-1] + a.slice(a.size/2 until a.size - 1).reversed()).joinToString(" ")
    }
}

fun main(args: Array<String>) {
    val t = readLine()!!.trimEnd().toInt()
    for (i in 0 until t) {
        val n = readLine()!!.trimEnd()
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. */
        val a = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray().sorted()
        val result = (a.slice(0..a.size/2 -1 ) + a[a.size-1] + a.slice(a.size/2 until a.size - 1).reversed()).joinToString(" ")
        println(result)
    }
}

