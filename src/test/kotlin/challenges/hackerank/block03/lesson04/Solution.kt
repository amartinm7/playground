package challenges.hackerank.block03.lesson04

/**
 * Palindrome
 *
 * return the position of the character with is breaking the palindrome
 *
 * aaab -> char on position #3
 * baa -> char on position #0
 * aaa -> #-1
 */
class Solution {
    /**
     * good solution
     */
    fun solution(s: String): Int {
        val isPalindrome: (String) -> Boolean = { slice ->
            slice == slice.reversed()
        }
        val isPalindromeArr: (List<Char>) -> Boolean = { arr ->
            arr == arr.reversed()
        }
        if (isPalindrome(s)) {
            return -1
        }
        val arr = s.toCharArray()
        for (i in s.indices) {
            val leftChar = arr[i]
            val rightChar = arr[s.length - i - 1]
            if (leftChar != rightChar) {
                val slice = arr.slice(0 until i) + arr.slice(i + 1 until s.length)
                if (isPalindromeArr(slice)) {
                    return i
                } else {
                    return s.length - i - 1
                }
            }
        }
        return -1
    }

    /**
     * good solution but bad performance
     */
    fun solution3(s: String): Int {
        val isPalindrome: (String) -> Boolean = { slice ->
            slice == slice.reversed()
        }
        if (isPalindrome(s)) {
            return -1
        }
        val arr = s.toCharArray()
        for (i in s.indices) {
            val leftChar = arr[i]
            val rightChar = arr[s.length - i - 1]
            if (leftChar != rightChar) {
                if (isPalindrome(s.removeRange(i, i + 1))) {
                    return i
                }
                if (isPalindrome(s.removeRange(s.length - i - 2 until s.length - i))) {
                    return s.length - i - 1
                }
            }
        }
        return -1
    }

    /**
     * good solution but very bad performance
     */
    fun solution2(s: String): Int {
        val isPalindrome: (String) -> Boolean = { slice ->
            slice == slice.reversed()
        }
        if (isPalindrome(s)) {
            return -1
        }
        for (i in s.indices) {
            val slice = s.removeRange(i, i + 1)
            if (isPalindrome(slice)) {
                return i
            }
        }
        return -1
    }
}

/*
 * Complete the 'palindromeIndex' function below.
 *
 * The function is expected to return an INTEGER.
 * The function accepts STRING s as parameter.
 */

fun palindromeIndex(s: String): Int {
    return Solution().solution(s)
}

fun main(args: Array<String>) {
    val q = readLine()!!.trim().toInt()

    for (qItr in 1..q) {
        val s = readLine()!!

        val result = palindromeIndex(s)

        println(result)
    }
}

