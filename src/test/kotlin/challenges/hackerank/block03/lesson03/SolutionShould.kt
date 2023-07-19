package challenges.hackerank.block03.lesson03

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class SolutionShould {
    @ParameterizedTest(name = "{index}: input {0} is {1}")
    @MethodSource("provider")
    fun test(input: String, k: Int, expected: String) {
        val obtained = Solution().solution(input, k)
        Assertions.assertEquals(
                expected, obtained, "input $input: (obtained,expected) $obtained != $expected")
        obtained.let(::println)
    }

    private companion object {
        @JvmStatic
        fun provider(): Stream<Arguments> = Stream.of(
                Arguments.of("xy", 3, "ab"),
                Arguments.of("middle-Outz", 2, "okffng-Qwvb"),
                Arguments.of("abcdefghijklmnopqrstuvwxyz", 3, "defghijklmnopqrstuvwxyzabc"),
                Arguments.of("Hello_World!", 4, "Lipps_Asvph!"),
                Arguments.of("www.abc.xy", 87, "fff.jkl.gh"),
        )
    }
}
