package challenges.hackerank.block02.lesson02

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class SolutionShould {
    @ParameterizedTest(name = "{index}: input {0} is {1}")
    @MethodSource("provider")
    fun test(input: Array<Array<Int>>, expected: Int) {
        val obtained = Solution().solution(input)
        Assertions.assertEquals(
                expected, obtained, "input $input: (obtained,expected) $obtained != $expected")
        obtained.let(::println)
    }

    private companion object {
        @JvmStatic
        fun provider(): Stream<Arguments> = Stream.of(
                Arguments.of(arrayOf(arrayOf(1, 1, 1), arrayOf(1, 1, 1), arrayOf(1, 1, 1)), 0),
                Arguments.of(arrayOf(arrayOf(1, 2, 3), arrayOf(4, 5, 6), arrayOf(9, 8, 9)), 2),
        )
    }
}
