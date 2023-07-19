package challenges.codility.lessons.test3timecomplexity.test3tape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

  public int solution1(int[] A) {
    int length = A.length;
    if (length < 2) {
      return 0;
    }
    int sumLeft = A[0];
    int sumRight = Arrays.stream(A).sum() - A[0];
    List<Integer> resultList = new ArrayList<Integer>();
    resultList.add(Math.abs(sumLeft - sumRight));
    for (int i = 1; i < length - 1; i++) {
      sumLeft = sumLeft + A[i];
      sumRight = sumRight - A[i];
      resultList.add(Math.abs(sumLeft - sumRight));
    }
    return resultList.stream().min(Integer::compare).get();
  }

  // works but the performance is not good
  public int solution2(int[] A) {
    int length = A.length;
    if (length < 2) {
      return 0;
    }
    List<Integer> resultList = new ArrayList<Integer>();
    List<Integer> list = Arrays.stream(A).boxed().collect(Collectors.toList());
    for (int i = 1; i < length; i++) {
      int left = sum(list, 0, i);
      int right = sum(list, i, length);
      resultList.add(Math.abs(left - right));
    }
    return resultList.stream().min(Integer::compare).get();
  }

  private int sum(List<Integer> list, int startIndex, int endIndex) {
    return list.subList(startIndex, endIndex).stream()
        .mapToInt(Integer::intValue)
        .sum();
  }
}




