package baekjoon.basic_math2;

import java.util.Scanner;

public class Test_1978 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int cnt = 0;

        for(int i=0; i<num; i++) {
            int n = sc.nextInt();
            boolean flag = true;

            if (n == 1) continue;
            if (n == 2 || n == 3) {
                cnt++;
                continue;
            }

            for(int j=2; j<n; j++) {
                if(n % j == 0) {
                    flag = false;
                    break;
                }
            }

            if(flag) cnt++;
        }

        System.out.print(cnt);
    }
}
