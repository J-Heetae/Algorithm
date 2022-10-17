package DynamicProgramming;

import java.util.Scanner;

//돌다리 건너기
public class Test_221010_90 {
    static int[] dy;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int answer = 0;
        dy = new int[n + 2];
        dy[1] = 1;
        dy[2] = 2;

        for(int i=3; i<n+2; i++) {
            dy[i] = dy[i - 1] + dy[i - 2];
        }

        System.out.print(dy[n+1]);
    }
}
