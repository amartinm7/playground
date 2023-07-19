package challenges.hackerank.block04.lesson03

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class SolutionShould {
    @ParameterizedTest(name = "{index}: input {0} is {1}")
    @MethodSource("provider")
    fun test(n: Array<Int>, expected: Int) {
        val obtained = Solution().solution(n)
        Assertions.assertEquals(
                expected, obtained, "input $n: (obtained,expected) $obtained != $expected")
        obtained.let(::println)
    }

    private companion object {
        @JvmStatic
        fun provider(): Stream<Arguments> = Stream.of(
                // Arguments.of(arrayOf(1,2,3,5,4,6,7,8), 1),
                // Arguments.of(arrayOf(8,2,3,5,4,6,7,1), -1),
                // Arguments.of(arrayOf(2,1,5,3,4), 3),
                Arguments.of(arrayOf(2,5,1,3,4), -1),
        )
    }
}
