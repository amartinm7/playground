package challenges.hackerank.training.lesson01

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class SolutionShould {
    @ParameterizedTest(name = "{index}: input {0} is {1}")
    @MethodSource("provider")
    fun test(input: Array<String>, expected: Array<String>) {
        val obtained = Solution().cavityMap(input)
        Assertions.assertEquals(
                expected.toList(), obtained.toList(), "input $input: (obtained,expected) $obtained != $expected")
    }

    private companion object {
        @JvmStatic
        fun provider(): Stream<Arguments> = Stream.of(
                Arguments.of(arrayOf("1112", "1912", "1892", "1234"), arrayOf("1112", "1X12", "18X2", "1234")),
        )
    }
}
