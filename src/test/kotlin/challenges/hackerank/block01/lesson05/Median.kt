package challenges.hackerank.block01.lesson05

class Median {

    fun findMedian(arr: Array<Int>): Int {
        // Write your code here
        val orderedList = arr.toMutableList().sorted()
        return orderedList[arr.size / 2]
    }

    fun main(args: Array<String>) {
        val n = readLine()!!.trim().toInt()

        val arr = readLine()!!.trimEnd().split(" ").map { it.toInt() }.toTypedArray()

        val result = findMedian(arr)

        println(result)
    }
}