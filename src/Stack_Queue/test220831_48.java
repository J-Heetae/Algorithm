package Stack_Queue;

import java.util.*;

//올바른 괄호
public class test220831_48 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        Stack<Character> stack = new Stack<>();
        String answer = "YES";

        // "(" 이면 스택에 PUSH ")"이면 스택에서 POP
        for (char c : str.toCharArray()) {
            if(c == '(') stack.push(c);
            else {
                //만약 ")"인데 스택이 비어있으면 NO
                if(stack.isEmpty()) {
                    answer = "NO";
                    break;
                } else {
                    stack.pop();
                }
            }
        }

        //for문이 끝났는데 스택이 비어있지 않으면 NO
        if(!stack.isEmpty()) answer = "NO";

        System.out.print(answer);
    }
}
