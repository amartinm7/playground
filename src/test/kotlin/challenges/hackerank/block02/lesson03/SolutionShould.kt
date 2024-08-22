package challenges.hackerank.block02.lesson03

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class SolutionShould {
    @ParameterizedTest(name = "{index}: input {0} is {1}")
    @MethodSource("provider")
    fun test(input: Array<Int>, expected: Array<Int>) {
        val obtained = Solution().solution(input)
        Assertions.assertEquals(
                expected.toList(), obtained.toList().subList(0, expected.size), "input $input: (obtained,expected) $obtained != $expected")
        obtained.let(::println)
    }

    private companion object {
        @JvmStatic
        fun provider(): Stream<Arguments> = Stream.of(
                Arguments.of(arrayOf(1, 1, 1), arrayOf(0, 3, 0, 0)),
                Arguments.of(arrayOf(1, 2, 3), arrayOf(0, 1, 1, 1)),
                Arguments.of(arrayOf(3, 1, 1), arrayOf(0, 2, 0, 1)),
        )
    }
}
