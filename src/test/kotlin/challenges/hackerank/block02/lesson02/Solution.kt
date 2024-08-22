package challenges.hackerank.block02.lesson02

import kotlin.math.abs

/**
Given a square matrix, calculate the absolute difference between the sums of its diagonals.

For example, the square matrix  is shown below:

1 2 3
4 5 6
9 8 9
The left-to-right diagonal = . The right to left diagonal = . Their absolute difference is .

Function description

Complete the  function in the editor below.

diagonalDifference takes the following parameter:

int arr[n][m]: an array of integers
Return

int: the absolute diagonal difference
Input Format

The first line contains a single integer, , the number of rows and columns in the square matrix .
Each of the next  lines describes a row, , and consists of  space-separated integers .

Constraints

Output Format

Return the absolute difference between the sums of the matrix's two diagonals as a single integer.
 **/
class Solution {

    /*
    * Complete the 'diagonalDifference' function below.
    *
    * The function is expected to return an INTEGER.
    * The function accepts 2D_INTEGER_ARRAY arr as parameter.
    */
    fun solution(arr: Array<Array<Int>>): Int {
        var mainCounter = 0
        for (index in arr.indices)  {
            mainCounter += arr[index][index]
        }
        var inverseCounter = 0
        for (index in arr.size - 1 downTo 0)  {
            inverseCounter += arr[arr.size -1 - index][index]
        }
        return abs(mainCounter - inverseCounter)
    }

    fun diagonalDifference(arr: Array<Array<Int>>): Int {
        // Write your code here
        return solution(arr)
    }

    fun main(args: Array<String>) {
        val n = readLine()!!.trim().toInt()

        val arr = Array<Array<Int>>(n, { Array<Int>(n, { 0 }) })

        for (i in 0 until n) {
            arr[i] = readLine()!!.trimEnd().split(" ").map{ it.toInt() }.toTypedArray()
        }

        val result = diagonalDifference(arr)

        println(result)
    }
}