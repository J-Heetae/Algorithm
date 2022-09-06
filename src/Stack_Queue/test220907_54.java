package Stack_Queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//교육과정 설계
public class test220907_54 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String required = sc.nextLine();
        String subjects = sc.nextLine();
        String answer = "NO";

        Queue<Character> que = new LinkedList<>();
        for(char c : required.toCharArray()) que.offer(c);

        for(char c : subjects.toCharArray()) {
            if(c == que.peek()) que.poll();
            if(que.size() == 0) {
                answer = "YES";
                break;
            }
        }
        System.out.print(answer);
    }
}
