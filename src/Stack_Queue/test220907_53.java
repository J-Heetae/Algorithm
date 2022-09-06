package Stack_Queue;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//공주구하기
public class test220907_53 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int princeNum = sc.nextInt(); //왕자의 수
        int removeNum = sc.nextInt(); //탈락 번호

        //왕자 생성
        Queue<Integer> que = new LinkedList<>();
        for(int i=0; i<princeNum; i++) que.offer(i + 1);

        while(que.size() > 1) {
            for(int i=1; i<removeNum; i++) que.offer(que.poll());
            que.poll();
        }

        System.out.println(que.peek());
    }
}
