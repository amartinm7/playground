package challenges.codility.company.openbk.lesson1;

class Solution {

  public static void main (String[] args) {
    new Solution().solution("codility");
  }

  public String solution(String S) {
    int[] charCount = new int[26];
    for (char c : S.toCharArray()) {
      charCount[c - 'a']++;
      if (charCount[c - 'a'] > 1) {
        return String.valueOf(c);
      }
    }
    return "";
  }
}
