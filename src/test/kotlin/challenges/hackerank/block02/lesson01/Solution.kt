package challenges.hackerank.block02.lesson01

/**
Given an array of integers, where all elements but one occur twice, find the unique element.

Example

The unique element is .

Function Description

Complete the lonelyinteger function in the editor below.

lonelyinteger has the following parameter(s):

int a[n]: an array of integers
Returns

int: the element that occurs only once
Input Format

The first line contains a single integer, , the number of integers in the array.
The second line contains  space-separated integers that describe the values in .

Constraints

It is guaranteed that  is an odd number and that there is one unique element.
, where .
 **/
class Solution {

    /*
     * Complete the 'lonelyinteger' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY a as parameter.
     */
    fun solution(arr: Array<Int>): Int {
        val orderedList = arr.toMutableList().sorted()
        for (i in orderedList.indices step 2) {
            if (i == orderedList.size - 1) {
                return orderedList[i]
            }
            if (orderedList[i] != orderedList[i + 1]) {
                return orderedList[i]
            }
        }
        return -1
    }

    fun lonelyinteger(a: Array<Int>): Int {
        // Write your code here
        return solution(a)
    }

    fun main(args: Array<String>) {
        val n = readLine()!!.trim().toInt()

        val a = readLine()!!.trimEnd().split(" ").map { it.toInt() }.toTypedArray()

        val result = lonelyinteger(a)

        println(result)
    }
}