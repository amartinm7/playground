package challenges.hackerank.block01.lesson02

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.math.BigInteger
import java.util.stream.Stream

class SolutionShould {
    @ParameterizedTest(name = "{index}: input {0} is expectedGap {1} in binary")
    @MethodSource("provider")
    fun test(input: Array<Int>, expected: List<BigInteger>) {
        val obtained = Solution().solution(input)
        Assertions.assertEquals(
                expected, obtained, "input $input: (obtained,expected) $obtained != $expected")
        obtained.forEach(::println)
    }

    private companion object {
        @JvmStatic
        fun provider(): Stream<Arguments> = Stream.of(
//                Arguments.of(arrayOf(1, 2, 3, 4, 5), listOf(10, 14)),
//                Arguments.of(arrayOf(9, 9, 9, 9, 9), listOf(36, 36)),
                Arguments.of(arrayOf(999999999, 999999999, 999999999, 999999999, 999999999),
                        listOf<BigInteger>(BigInteger.valueOf(3999999996), BigInteger.valueOf(3999999996))),
        )
    }
}
