package challenges.codility.lessons.test2arrays.test1cyclicRotation;

public class Solution {
  public int[] solution(int[] A, int K) {
    int[] newList = new int[A.length];
    for (int i = 0; i < A.length; i++) {
      newList[(i + K) % A.length] = A[i];
    }
    return newList;
  }
}


