package challenges.codility.lessons.test1iterations;


import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
A binary gap within a positive integer N is any maximal sequence of consecutive zeros that is surrounded by ones at both ends in the binary representation of N.

For example, number 9 has binary representation 1001 and contains a binary gap of length 2. The number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3. The number 20 has binary representation 10100 and contains one binary gap of length 1. The number 15 has binary representation 1111 and has no binary gaps. The number 32 has binary representation 100000 and has no binary gaps.

Write a function:

class Solution { public int solution(int N); }

that, given a positive integer N, returns the length of its longest binary gap. The function should return 0 if N doesn't contain a binary gap.

For example, given N = 1041 the function should return 5, because N has binary representation 10000010001 and so its longest binary gap is of length 5. Given N = 32 the function should return 0, because N has binary representation '100000' and thus no binary gaps.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [1..2,147,483,647].
 */
public class SolutionShould {

  @ParameterizedTest(name = "{index}: input {0} is expectedGap {1} in binary")
  @MethodSource("provider")
  public void test(int input, int expected ) {
    int obtained = new Solution().solution(input);
    assertEquals(
          expected, obtained, format("input %s: (obtained,expected) %s != %s", input, obtained, expected)
    );
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
          Arguments.of(6, 0),
          Arguments.of(328, 2),
          Arguments.of(15, 0),
          Arguments.of(1041, 5),
          Arguments.of(1, 0),
          Arguments.of(2, 0),
          Arguments.of(147, 2),
          Arguments.of(483, 3),
          Arguments.of(647, 4)
    );
  }
}
