package challenges.codility.lessons.test3timecomplexity.test2permmissingelem;

import java.util.Arrays;

public class Solution {
  public int solution(int[] A) {
    if (A.length == 0) {
      return 1;
    }
    int max = Arrays.stream(A).max().getAsInt();
    int sumaMax = 0;
    for (int i = 1; i < max + 1; i++) {
      sumaMax = sumaMax + i;
    }
    int suma = Arrays.stream(A).sum();
    if (sumaMax - suma == 0) {
      return max + 1;
    } else {
      return sumaMax - suma;
    }
  }
}




