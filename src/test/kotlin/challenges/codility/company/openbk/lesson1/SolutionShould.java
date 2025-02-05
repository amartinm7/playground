package challenges.codility.company.openbk.lesson1;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 */
public class SolutionShould {

  @ParameterizedTest(name = "{index}: input {0} is expectedGap {1} in binary")
  @MethodSource("provider")
  public void test(String input, String expected) {
    String obtained = new Solution().solution(input);
    assertEquals(
        expected, obtained, format("input %s: (obtained,expected) %s != %s", input, obtained, expected)
    );
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of("aba", "a"),
        Arguments.of("zz", "z"),
        Arguments.of("codility", "i")
    );
  }
}
