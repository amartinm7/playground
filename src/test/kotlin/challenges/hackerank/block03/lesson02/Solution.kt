package challenges.hackerank.block03.lesson02

/**
 * Tower Breakers
 *
 * If n is even or m is 1, the first player (Player 1) will always win. Player 1 can continuously reduce the towers to a height of 1, leaving the second player (Player 2) with no possible moves.
 *
 * If n is odd and m is greater than 1, the second player (Player 2) will always win. Player 2 can mimic Player 1's moves, ensuring that there will always be a possible move available.
 *
 * Returning the Result: Based on the analysis above, you can return the result accordingly:
 *
 * If n is even or m is 1, return 2 (indicating that Player 2 wins).
 * If n is odd and m is greater than 1, return 1 (indicating that Player 1 wins).
 */
class Solution {
    fun solution(n: Int, m: Int): Int {
        val isEven: (Int) -> Boolean = { it % 2 == 0 }
        val isOdd: (Int) -> Boolean = { !isEven(it) }
        return when {
            m == 1 -> 2
            isEven(n) -> 2
            isOdd(n) && m > 1 -> 1
            else -> -1 //never happens
        }
    }
}

/*
 * Complete the 'towerBreakers' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts following parameters:
 *  1. INTEGER n
 *  2. INTEGER m
 */

fun towerBreakers(n: Int, m: Int): Int {
    // Write your code here
    return Solution().solution(n,m)
}

fun main(args: Array<String>) {
    val t = readLine()!!.trim().toInt()

    for (tItr in 1..t) {
        val first_multiple_input = readLine()!!.trimEnd().split(" ")

        val n = first_multiple_input[0].toInt()

        val m = first_multiple_input[1].toInt()

        val result = towerBreakers(n, m)

        println(result)
    }
}
