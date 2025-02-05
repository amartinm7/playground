package challenges.codility.lessons.test3timecomplexity.test2permmissingelem;


import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
An array A consisting of N different integers is given.
The array contains integers in the range [1..(N + 1)], which means that exactly one element is missing.

Your goal is to find that missing element.

Write a function:

class Solution { public int solution(int[] A); }

that, given an array A, returns the value of the missing element.

For example, given array A such that:

  A[0] = 2
  A[1] = 3
  A[2] = 1
  A[3] = 5
the function should return 4, as it is the missing element.

Write an efficient algorithm for the following assumptions:

N is an integer within the range [0..100,000];
the elements of A are all distinct;
each element of array A is an integer within the range [1..(N + 1)].
 */
public class SolutionShould {

  @ParameterizedTest(name = "{index}: given: {0}, expected: {1}")
  @MethodSource("provider")
  public void test(int[] given, int expected) {
    int obtained = new Solution().solution(given);
    assertEquals(
          expected, obtained, format("input %s: (obtained,expected) %s != %s", given, obtained, expected)
    );
  }

  private static Stream<Arguments> provider() {
    return Stream.of(
        Arguments.of(new int[]{2,3,1,5}, 4),
        Arguments.of(new int[]{1,3,4,5}, 2),
        Arguments.of(new int[]{2}, 1),
        Arguments.of(new int[]{1,3}, 2),
        Arguments.of(new int[]{2,3}, 1),
        Arguments.of(new int[]{1,2,3}, 4),
        Arguments.of(new int[]{}, 1),
        Arguments.of(new int[]{2,3,4,5}, 1),
        Arguments.of(new int[]{1,2,3,4}, 5)
    );
  }
}

  // List<Integer> list = Arrays.stream(A).boxed().collect(Collectors.toList());
