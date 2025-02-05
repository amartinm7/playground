package challenges.codility.lessons.test5.brackets;

import java.util.Stack;

class Solution {
  public int solution(String S) {
    Stack<Character> stack = new Stack<Character>();
    for (char c : S.toCharArray()) {
      if (c == '(' || c == '{' || c == '[') {
        stack.push(c);
      } else if (c == ')' || c == '}' || c == ']') {
        if (stack.isEmpty()) {
          return 0;
        }
        char top = stack.pop();
        if ((c == ')' && top != '(') || (c == '}' && top != '{') || (c == ']' && top != '[')) {
          return 0;
        }
      }
    }
    return stack.isEmpty() ? 1 : 0;
  }
}
