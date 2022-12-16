package baekjoon.basic_math1;

import java.util.Scanner;

public class Test_2292 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int num2 = num;

        int cnt = 1;
        while(true) {
            int tmp = cnt * 6;

            if(num2 <= tmp + 1) {
                break;
            } else {
                num2 = num2 - tmp;
                cnt++;
            }
        }

        if(num == 1) System.out.print(1);
        else System.out.print(cnt + 1);
    }
}
