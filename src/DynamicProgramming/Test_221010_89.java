package DynamicProgramming;

import java.util.Scanner;

//계단오르기
public class Test_221010_89 {

    static int n;
    static int answer = 0;

    static void DFS(int v) {
        if(v == n) {
            answer++;
            return;
        } else if(v > n) {
            return;
        } else {
            DFS(v + 1);
            DFS(v + 2);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        DFS(0);

        System.out.print(answer);
    }
}
