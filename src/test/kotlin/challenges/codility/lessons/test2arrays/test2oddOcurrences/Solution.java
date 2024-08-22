package challenges.codility.lessons.test2arrays.test2oddOcurrences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Solution {
  public int solution(int[] A) {
    OddNumberMap oddNumberMap = new OddNumberMap();
    Arrays.stream(A).forEach(item -> oddNumberMap.add(item));
    return oddNumberMap.unpair();
  }

  private class OddNumber{
    private List<Integer> numberList;
    public OddNumber() {
      numberList = new ArrayList<Integer>();
    }

    public void add(int item) {
      numberList.add(item);
    }

    public boolean isUnpair() {
      return (numberList.size() % 2) == 1;
    }

    @Override
    public String toString() {
      return "OddNumber: " + Arrays.toString(numberList.toArray());
    }
  }

  private class OddNumberMap {
    private Map<Integer, OddNumber> map;

    public OddNumberMap() {
      map = new HashMap<Integer, OddNumber>();
    }

    public void add(int item) {
      OddNumber oddNumber = map.getOrDefault(item, new OddNumber());
      oddNumber.add(item);
      map.put(item, oddNumber);
    }

    public int unpair() {
      // map.values().stream().forEach(System.out::println);
      List<OddNumber> result = map.values().stream().filter(predicate).collect(Collectors.toList());
      if (result.size() == 1) {
        return result.get(0).numberList.get(0);
      }
      return 0;
    }

    private Predicate<OddNumber> predicate =  oddNumber -> oddNumber.isUnpair();
  }
}




