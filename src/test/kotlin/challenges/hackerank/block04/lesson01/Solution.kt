package challenges.hackerank.block04.lesson01

/**
 * Complete the 'gridChallenge' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts STRING_ARRAY grid as parameter.
 */
class Solution {
    fun solution(grid: Array<String>): String {
        val transform: (String) -> String = {
            it.toCharArray().sortedArray().joinToString("")
        }
        val orderList = grid.map{transform(it)}
        val mutableList = mutableListOf<String>()
        for (i in orderList.indices) {
            val list = mutableListOf<String>()
            for (j in orderList[i].indices) {
                list.add(orderList[j].slice(i ..i))
            }
            mutableList.add(list.joinToString(""))
        }
        val orderedMutableList = mutableList.map{transform(it)}
        if (mutableList == orderedMutableList) return "YES" else return "NO"
    }
}

/*
 * Complete the 'gridChallenge' function below.
 *
 * The function is expected to return a STRING.
 * The function accepts STRING_ARRAY grid as parameter.
 */

fun gridChallenge(grid: Array<String>): String {
    // Write your code here
    return Solution().solution(grid)
}

fun main(args: Array<String>) {
    val t = readLine()!!.trim().toInt()

    for (tItr in 1..t) {
        val n = readLine()!!.trim().toInt()

        val grid = Array<String>(n, { "" })
        for (i in 0 until n) {
            val gridItem = readLine()!!
            grid[i] = gridItem
        }

        val result = gridChallenge(grid)

        println(result)
    }
}
