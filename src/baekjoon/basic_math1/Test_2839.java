package baekjoon.basic_math1;

import java.util.Scanner;

public class Test_2839 {
    static int[] arr = {5, 3};
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int cnt = 0;

        while(n > 15) {
            n -= 5;
            cnt++;
        }

        DFS(n, 0, cnt);

        if(answer == Integer.MAX_VALUE) System.out.print(-1);
        else System.out.print(answer);
    }

    static void DFS(int n, int num, int cnt) {
        if(num > n) return;
        if(num == n) {
            answer = Math.min(cnt, answer);
        } else {
            for(int i=0; i<2; i++) {
                DFS(n, num + arr[i], cnt+1);
            }
        }
    }
}
