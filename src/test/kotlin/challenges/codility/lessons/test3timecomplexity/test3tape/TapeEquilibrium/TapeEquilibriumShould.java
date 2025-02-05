package challenges.codility.lessons.test3timecomplexity.test3tape.TapeEquilibrium;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TapeEquilibriumShould {

  @ParameterizedTest(name = "{index}: given: {0}, expected: {1}")
  @MethodSource("provider")
  public void verifySolution(int[] pA, int pExpectedMissingValue) {
    assertEquals(new TapeEquilibrium().solution(pA), pExpectedMissingValue);
  }
  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(new int[]{3, 1, 2, 4, 3}, 1),
        Arguments.of(new int[]{-3, 1, 2, -4, 3}, 1),
        Arguments.of(new int[]{5, 2, 7, 10}, 4),
        Arguments.of(new int[]{-1000, 1000, -500, 990}, 490),
        Arguments.of(new int[]{1, 2}, 1),
        Arguments.of(new int[]{100, -25}, 125)
    );
  }
}
