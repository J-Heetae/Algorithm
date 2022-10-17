package DynamicProgramming;

import java.util.Scanner;

//계단오르기
public class Test_221010_89_2 {
    static int[] dy;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int answer = 0;
        dy = new int[n + 1];
        dy[1] = 1;
        dy[2] = 2;

        for(int i=3; i<=n; i++) {
            dy[i] = dy[i - 1] + dy[i - 2];
        }

        answer = dy[n];

        System.out.print(answer);
    }
}
