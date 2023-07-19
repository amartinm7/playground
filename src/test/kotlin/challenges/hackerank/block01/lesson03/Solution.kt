package challenges.hackerank.block01.lesson03

import kotlin.math.abs

/**
 * Given a time in -hour AM/PM format, convert it to military (24-hour) time.
 *
 * Note: - 12:00:00AM on a 12-hour clock is 00:00:00 on a 24-hour clock.
 * - 12:00:00PM on a 12-hour clock is 12:00:00 on a 24-hour clock.
 *
 * Example
 *
 *
 * Return '12:01:00'.
 *
 *
 * Return '00:01:00'.
 *
 * Function Description
 *
 * Complete the timeConversion function in the editor below. It should return a new string representing the input time in 24 hour format.
 *
 * timeConversion has the following parameter(s):
 *
 * string s: a time in  hour format
 * Returns
 *
 * string: the time in  hour format
 * Input Format
 *
 * A single string  that represents a time in -hour clock format (i.e.:  or ).
 *
 * Constraints
 *
 * All input times are valid
 * Sample Input
 *
 * 07:05:45PM
 * Sample Output
 *
 * 19:05:45
 */
class Solution {

    data class Time(val hour: Int, val minutes: Int, val seconds: Int, val format: String) {
        private fun Int.fmt() = "%02d".format(this)
        fun getHourOn24Format(): String =
                when (format) {
                    "AM" -> "${abs((hour % 12) % 24).fmt()}:${minutes.fmt()}:${seconds.fmt()}"
                    "PM" -> "${abs((hour % 12) + 12).fmt()}:${minutes.fmt()}:${seconds.fmt()}"
                    else -> ""
                }

        companion object {
            fun of(timeInHourFormat: String): Time =
                    timeInHourFormat.split(":").let {
                        Time(it[0].toInt(), it[1].toInt(), it[2].slice(0..1).toInt(), it[2].slice(2..3))
                    }
        }
    }

    fun solution(timeInHourFormat: String): String =
            Time.of(timeInHourFormat).getHourOn24Format();

    fun timeConversion(s: String): String {

        // Write your code here
        return solution(s)
    }

    fun main(args: Array<String>) {
        val s = readLine()!!

        val result = timeConversion(s)

        println(result)
    }

    fun timeConversion2(s: String): String {
        data class Time(val hour: Int, val minutes: Int, val seconds: Int, val format: String) {
            private fun Int.fmt() = "%02d".format(this)
            fun getHourOn24Format(): String =
                    when (format) {
                        "AM" -> "${abs(12 - hour).fmt()}:${minutes.fmt()}:${seconds.fmt()}"
                        "PM" -> "${abs(12 + hour).fmt()}:${minutes.fmt()}:${seconds.fmt()}"
                        else -> ""
                    }
        }

        fun of(timeInHourFormat: String): Time =
                timeInHourFormat.split(":").let {
                    Time(it[0].toInt(), it[1].toInt(), it[2].slice(0..1).toInt(), it[2].slice(2..3))
                }

        // Write your code here
        return of(s).getHourOn24Format();
    }

}