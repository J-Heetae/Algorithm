package Stack_Queue;

import java.util.*;

//괄호 문자 제거
public class test220831_49 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String answer = "";
        Stack<Character> stack = new Stack<>();

        for (char c : str.toCharArray()) {
            // '('는 stack에 push ')'이면 pop
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                stack.pop();
            } else {
                //stack이 비어있으면 answer에 문자 저장
                if (stack.isEmpty()) answer += c + "";
            }
        }
        System.out.print(answer);
    }
    
}
