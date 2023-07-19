package challenges.codility.lessons.test3timecomplexity.test3tape.TapeEquilibrium;

public class TapeEquilibrium {
  public int solution(int[] A) {
    long sumAllElements = 0;
    for(int i=0; i<A.length; i++) {
      sumAllElements += A[i];
    }

    int minDifference = Integer.MAX_VALUE;
    int currentDifference = Integer.MAX_VALUE;
    long sumLeft = 0;
    long sumRight = 0;

    for(int p=0; p<A.length-1; p++) {
      sumLeft += A[p];
      sumRight = sumAllElements - sumLeft;
      currentDifference = (int) Math.abs(sumLeft - sumRight);
      minDifference = Math.min(currentDifference, minDifference);
    }
    return minDifference;
  }
}
