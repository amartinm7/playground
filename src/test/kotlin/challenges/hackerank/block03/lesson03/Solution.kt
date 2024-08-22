package challenges.hackerank.block03.lesson03

class Solution {

    fun solution(s: String, k: Int): String {
        val kk = k % 26
        // Write your code here
        return s.toCharArray().toTypedArray()
                .map { it ->
                    when {
                        it == '-' -> it
                        it == '!' -> it
                        it.isLowerCase() && it + kk <= 'z' -> it + kk
                        it.isUpperCase() && it + kk <= 'Z' -> it + kk
                        it.isLowerCase() && it + kk > 'z' -> (((it.toInt() + kk) % 'z'.toInt()) + 'a'.toInt() - 1).toChar()
                        it.isUpperCase() && it + kk > 'Z' -> (((it.toInt() + kk) % 'Z'.toInt()) + 'A'.toInt() - 1).toChar()
                        else -> it
                    }
                }
                .joinToString("")
    }
}

fun caesarCipher(s: String, k: Int): String {
    // Write your code here
    return Solution().solution(s, k)
}

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()

    val s = readLine()!!

    val k = readLine()!!.trim().toInt()

    val result = caesarCipher(s, k)

    println(result)
}