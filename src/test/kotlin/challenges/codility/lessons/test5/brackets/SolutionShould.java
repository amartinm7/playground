package challenges.codility.lessons.test5.brackets;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:

        S is empty;
        S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string;
        S has the form "VW" where V and W are properly nested strings.

For example, the string "{[()()]}" is properly nested but "([)()]" is not.

Write a function:

    class Solution { public int solution(String S); }

that, given a string S consisting of N characters, returns 1 if S is properly nested and 0 otherwise.

For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the function should return 0, as explained above.

Write an efficient algorithm for the following assumptions:

        N is an integer within the range [0..200,000];
        string S is made only of the following characters: '(', '{', '[', ']', '}' and/or ')'.
 */
public class SolutionShould {

  @ParameterizedTest(name = "{index}: input {0} is expectedGap {1} in binary")
  @MethodSource("provider")
  public void test(String input, int expected ) {
    int obtained = new Solution().solution(input);
    assertEquals(
          expected, obtained, format("input %s: (obtained,expected) %s != %s", input, obtained, expected)
    );
  }
  private static Stream<Arguments> provider() {
    return Stream.of(
          Arguments.of("{[()()]}", 1),
          Arguments.of("{[()()]", 0),
          Arguments.of("{[()()]}{[()()]", 0),
          Arguments.of("{[()()()]}", 1)
    );
  }
}
