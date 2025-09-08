import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        int answer = 0;

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if(c == '(') {
                stack.push(c);
                continue;
            }

            if(str.charAt(i - 1) == '(') { //레이저
                stack.pop();
                answer += stack.size();
                continue;
            }

            stack.pop();
            answer++;
        }
        System.out.print(answer);
    }
}