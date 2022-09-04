package Stack_Queue;

import java.util.Scanner;
import java.util.Stack;

//후위식 연산(posetfix)
public class test220904_51 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        Stack<Integer> stack = new Stack<>();

        for(char c : str.toCharArray()) {
            // 문자의 값을 하나씩 꺼내서 숫자면 stack에 push(Integer의 stack에 Character를 넣어주기 때문에 Aaski로 들어감, 따라서 -48)
            if(Character.isDigit(c)) stack.push(c - 48);
            else {
                // stack에 쌓인 숫자 두개를 꺼내서 연산자에 맞춰 연산 후 다시 push
                // "53-"인 경우 중위식 연산으로 "5-3"이기 때문에 늦게 push된 3이 rt 먼저 push된 5가 lt
                int rt = stack.pop();
                int lt = stack.pop();
                if(c == '+') stack.push(lt + rt);
                else if(c == '-') stack.push(lt - rt);
                else if(c == '/') stack.push(lt / rt);
                else if(c == '*') stack.push(lt * rt);
            }
        }
        // 인덱스 0번 출력
        System.out.print(stack.get(0));
    }
}
