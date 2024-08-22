package challenges.codility.lessons.test1iterations;

import java.util.Arrays;
import java.util.function.Predicate;

public class Solution {
  public int solution(int input) {
    String binaryString = Integer.toBinaryString(input);
    while (checker.test(binaryString)) {
      binaryString = binaryString.substring(0, binaryString.length() - 1);
    }
    if (binaryString.indexOf("0") == -1) {
      return 0;
    }
    if (binaryString.length() < 2) {
      return 0;
    }
    return Arrays.stream(
                binaryString.split("1")
          )
          .mapToInt(String::length)
          .max()
          .getAsInt();
  }

  private Predicate<String> checker =
        binaryString -> binaryString.substring(binaryString.length() - 1, binaryString.length()).equals("0");
}


