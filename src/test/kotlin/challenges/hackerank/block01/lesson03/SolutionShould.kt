package challenges.hackerank.block01.lesson03

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class SolutionShould {
    @ParameterizedTest(name = "{index}: input {0} is expected {1}")
    @MethodSource("provider")
    fun test(input: String, expected: String) {
        val obtained = Solution().solution(input)
        Assertions.assertEquals(
                expected, obtained, "input $input: (obtained,expected) $obtained != $expected")
        obtained.apply(::println)
    }

    private companion object {
        @JvmStatic
        fun provider(): Stream<Arguments> = Stream.of(
                Arguments.of("07:05:45PM", "19:05:45"),
                Arguments.of("12:00:00AM", "00:00:00"),
                Arguments.of("12:45:54PM", "12:45:54"),
                Arguments.of("04:59:59AM", "04:59:59"),
        )
    }
}
