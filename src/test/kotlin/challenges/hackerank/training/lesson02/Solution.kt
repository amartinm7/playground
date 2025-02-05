package challenges.hackerank.training.lesson02

import kotlin.math.roundToInt

class Solution {
}
/*
 * Complete the 'solve' function below.
 *
 * Calculate the cost of the meal:
 * meal + tips + taxes
 *
 * %tips = tips * (meal / 100)
 * %taxs = taxes * (meal / 100)
 *
 * cost = (meal + %tips + %taxs) round to int
 *
 * The function accepts following parameters:
 *  1. DOUBLE meal_cost
 *  2. INTEGER tip_percent
 *  3. INTEGER tax_percent
 */

fun solve(meal_cost: Double, tip_percent: Int, tax_percent: Int): Unit {
    // Write your code here
    val tip = tip_percent * (meal_cost / 100)
    val tax = tax_percent * (meal_cost / 100)
    val cost = (meal_cost + tip + tax).roundToInt()
    println(cost)

}

fun main(args: Array<String>) {
    val meal_cost = readLine()!!.trim().toDouble()

    val tip_percent = readLine()!!.trim().toInt()

    val tax_percent = readLine()!!.trim().toInt()

    solve(meal_cost, tip_percent, tax_percent)
}