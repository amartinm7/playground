package challenges.hackerank.training.lesson01

class Solution {
    /*
    * Complete the 'cavityMap' function below.
    *
    * The function is expected to return a STRING_ARRAY.
    * The function accepts STRING_ARRAY grid as parameter.
    */
    fun cavityMap(grid: Array<String>): Array<String> {
        val length = grid[0].length
        val outputGrid = grid.toList().map { it -> it.toCharArray() }.map { it -> it.toIntArray() }
        val copyGrid = grid.toList().map { it -> it.toCharArray() }
        // Write your code here
        for (i in 1 until (length - 1)) {
            for (j in 1 until (length - 1)) {
                val current = outputGrid[i][j]
                val top = outputGrid[i-1][j]
                val bottom = outputGrid[i+1][j]
                val left = outputGrid[i][j-1]
                val right = outputGrid[i][j+1]
                if (current > top &&  current > bottom && current > left && current > right) {
                    copyGrid[i][j] = 'X'
                }
            }
        }
        return copyGrid.map { it -> String(it) }.toTypedArray()
    }

    private fun CharArray.toIntArray(): List<Int> {
        return this.map { it -> Character.getNumericValue(it) }
    }

    fun main(args: Array<String>) {
        val n = readLine()!!.trim().toInt()

        val grid = Array<String>(n, { "" })
        for (i in 0 until n) {
            val gridItem = readLine()!!
            grid[i] = gridItem
        }

        val result = cavityMap(grid)

        println(result.joinToString("\n"))
    }
}

