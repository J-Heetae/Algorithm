package Stack_Queue;

import java.util.Scanner;
import java.util.Stack;

//쇠막대기
public class test220906_52 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        Stack<Character> stack = new Stack<>();
        char last = ' ';
        int answer = 0;

        for(char c : str.toCharArray()) {
            if(c == '(') stack.push(c);
            else {
                stack.pop();
                if(last == ')') answer++;
                else answer += stack.size();
            }
            last = c;
        }

        System.out.println(answer);
    }
}
