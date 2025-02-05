package challenges.hackerank.block01.lesson01

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class SolutionShould {
    @ParameterizedTest(name = "{index}: input {0} is expectedGap {1} in binary")
    @MethodSource("provider")
    fun test(input: Array<Int>, expected: List<Float>) {
//        val obtained = Solution().solution(input)
//        Assertions.assertEquals(
//                expected, obtained, "input $input: (obtained,expected) $obtained != $expected")
//        obtained.forEach(::println)
    }

    private companion object {
        @JvmStatic
        fun provider(): Stream<Arguments> = Stream.of(
                Arguments.of(arrayOf(1, 1, 0, -1, -1), listOf("0.400000", "0.400000", "0.200000")),
        )
    }
}
