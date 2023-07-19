package challenges.codility.lessons.test3timecomplexity.test1frog;

public class Solution {
  public int solution(int X, int Y, int D) {
    if (((Y - X) % D) == 0) {
      return ((Y - X) / D);
    } else {
      return ((Y - X) / D) + 1;
    }
  }
}




