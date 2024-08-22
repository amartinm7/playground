package challenges.hackerank.block03.lesson02

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class SolutionShould {
    @ParameterizedTest(name = "{index}: input {0} is {1}")
    @MethodSource("provider")
    fun test(n: Int, m: Int, expected: Int) {
        val obtained = Solution().solution(n, m)
        Assertions.assertEquals(
                expected, obtained, "input $n $m: (obtained,expected) $obtained != $expected")
        obtained.let(::println)
    }

    private companion object {
        @JvmStatic
        fun provider(): Stream<Arguments> = Stream.of(
                Arguments.of(2, 2, 1),
                Arguments.of(1, 4, 1),
        )
    }
}
