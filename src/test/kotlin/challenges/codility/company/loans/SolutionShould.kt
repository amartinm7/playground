package challenges.codility.company.loans

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class SolutionShould {
    @ParameterizedTest(name = "{index}: input {0} is expectedGap {1} in binary")
    @MethodSource("provider")
    fun test(input: Array<Pair<Int,Long>>, expected: Int) {
        val obtained = Solution().solution(input)
        Assertions.assertEquals(
                expected, obtained, "input $input: (obtained,expected) $obtained != $expected")
        println(obtained)
    }

    private companion object {
        @JvmStatic
        fun provider(): Stream<Arguments> = Stream.of(
                Arguments.of(arrayOf(Pair(5, 15L), Pair(10, 25L), Pair(30, 65L), Pair(40, 100L)), 15),
        )
    }
}
