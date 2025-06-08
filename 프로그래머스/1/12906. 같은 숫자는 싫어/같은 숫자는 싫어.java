import java.util.*;

public class Solution {
    public int[] solution(int[] arr) {
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        
        for(int i=0; i<n; i++) {
            if(stack.isEmpty() || stack.peek() != arr[i]) {
                stack.push(arr[i]);
            }
        }
        
        int[] answer = new int[stack.size()];
        int answerIdx = answer.length - 1;
        while(!stack.isEmpty()) {
            answer[answerIdx--] = stack.pop();
        }
        return answer;
    }
}