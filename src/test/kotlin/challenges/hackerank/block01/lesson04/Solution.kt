package challenges.hackerank.block01.lesson04


class Solution {
    /*
     * Complete the 'fizzBuzz' function below.
     *
     * The function accepts INTEGER n as parameter.
     */
    fun fizzBuzz(n: Int): Unit {

        // Write your code here
        for (i in 1..n) {
            when {
                (i % 3 == 0) && (i % 5 == 0) -> println("FizzBuzz")
                (i % 3 == 0) && !(i % 5 == 0) -> println("Fizz")
                !(i % 3 == 0) && (i % 5 == 0) -> println("Buzz")
                else -> println(i)
            }
        }

    }

    fun main(args: Array<String>) {
        val n = readLine()!!.trim().toInt()

        fizzBuzz(n)
    }
}