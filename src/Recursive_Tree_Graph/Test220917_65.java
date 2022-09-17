package Recursive_Tree_Graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//송아지 찾기(최단 거리 구하기)
public class Test220917_65 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int c = sc.nextInt();

        int answer = 0;
        int[] ch = new int[10001];
        int[] jump = {1, -1, 5};

        Queue<Integer> que = new LinkedList<>();
        que.offer(n);
        boolean flag = true;

        while(flag && !que.isEmpty()) {
            int size = que.size();

            for(int i=0; i<size; i++) {
                int now = que.poll();

                if(now == c) {
                    flag = false;
                    break;
                }

                for(int j : jump) {
                    int afterJump = now + j;

                    if((afterJump >= 1) && (afterJump <= 10000) && (ch[afterJump] == 0)) {
                        ch[afterJump]++;
                        que.offer(afterJump);
                    }
                }
            }

            if(flag)
                answer++;
            else
                break;
        }

        System.out.print(answer);
    }
}