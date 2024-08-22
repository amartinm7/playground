package lessons.algorithm.fibonacci

/**
 * fibonacci
 **/
class Solution {

    tailrec fun calculateNext(limit: Int, list: MutableList<Int>): List<Int> {
        if (limit == list.size) {
            return list
        } else {
            list.slice(list.size - 2 until list.size).sum().apply {
                list.add(this)
                return calculateNext(limit, list)
            }
        }
    }

    fun solution(n: Int): List<Int> {
        return calculateNext(n, mutableListOf(0 , 1))
    }
}

fun main(args: Array<String>) {
    val n = readLine()!!.trim().toInt()
    val arr = readLine()!!.trimEnd().split(" ").map { it.toInt() }.toTypedArray()
}